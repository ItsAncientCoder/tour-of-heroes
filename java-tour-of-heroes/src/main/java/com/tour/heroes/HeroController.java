package com.tour.heroes;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/heroes" })
@CrossOrigin(origins = "*", maxAge = 3600)
public class HeroController {

	List<Hero> listOfHeroes = new ArrayList<>();

	@PostConstruct
	private void loadHeroes() {
		Hero h1 = new Hero();
		h1.setId(0);
		h1.setName("prabhas");
		Hero h2 = new Hero();
		h2.setId(1);
		h2.setName("raana");
		listOfHeroes.add(h1);
		listOfHeroes.add(h2);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Hero> getListOfHeroes() {
		return listOfHeroes;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteHero(@PathVariable("id") int id) {
		if (id < listOfHeroes.size()) {
			listOfHeroes.remove(id);
		} else {
			new Exception("No hero found to delete ");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Hero getHero(@PathVariable(value = "id") int id) {
		Hero hero = null;
		if (id < listOfHeroes.size()) {
			hero = listOfHeroes.get(id);
		} else {
			new Exception("No hero found ");
		}
		return hero;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Hero> addHero(@RequestBody Hero hero) {
		hero.setId(listOfHeroes.size());
		listOfHeroes.forEach(n->{
			System.out.println(n.getId());
		});
		listOfHeroes.add(listOfHeroes.size(), hero);
		return new ResponseEntity<Hero>(hero, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Hero> updateHero(@RequestBody Hero hero) {
		int id = hero.getId();
		Hero newHero = listOfHeroes.set(id, hero);
		return new ResponseEntity<Hero>(newHero, HttpStatus.OK);
	}
}
