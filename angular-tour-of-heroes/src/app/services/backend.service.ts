import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class BackendService {

  API_PATH = '/oxygen/api/heroes';

  constructor(private httpClient: HttpClient) {}

  public getListOfHeroes() {
    this.httpClient.get(this.API_PATH);
  }
}
