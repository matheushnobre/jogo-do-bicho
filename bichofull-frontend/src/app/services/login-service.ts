import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { LoginRequest } from '../models/auth/login-request';
import { AuthResponse } from '../models/auth/auth-response';

@Injectable({
  providedIn: 'root',
})

export class LoginService {
  http = inject(HttpClient)

  API = "http://localhost:8080/api/auth"
  
  constructor(){ 

  }

  login(user: LoginRequest): Observable<AuthResponse>{
    console.log('service executado')
    return this.http.post<AuthResponse>(this.API + "/login", user);
  }
  
}
