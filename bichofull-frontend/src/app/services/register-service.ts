import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { RegisterRequest } from '../models/auth/register-request';

@Injectable({
  providedIn: 'root',
})

export class RegisterService {
  http = inject(HttpClient)
  API = "http://localhost:8080/api/auth"
  
  constructor(){}

  register(user: RegisterRequest): Observable<User>{
    return this.http.post<User>(this.API + '/register', user);
  }
}
