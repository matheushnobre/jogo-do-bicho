import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Animal } from '../models/animal';
import { map } from 'rxjs/operators';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class AnimalService {
  http = inject(HttpClient)
  
  API = `${environment.api_url}/animals`

  constructor(){}

  findAll() {
    return this.http.get<Animal[]>(this.API).pipe(
      map(data => data.map(a => Object.assign(new Animal(), a)))
    );
  }
  
}
