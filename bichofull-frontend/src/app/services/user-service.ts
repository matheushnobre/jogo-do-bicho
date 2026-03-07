import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class UserService {
  http = inject(HttpClient)
  private API = "http://localhost:8080/api/users"
  
  constructor(){}

  getProfile(): Observable<User>{
    return this.http.get<User>(this.API + '/me', {withCredentials: true});
  }
}
