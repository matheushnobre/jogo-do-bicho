import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LoginRequest } from '../dto/auth/login-request';
import { AuthResponse } from '../dto/auth/auth-response';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class LoginService {
  http = inject(HttpClient)
  API = `${environment.api_url}/auth`
  
  constructor(){ 

  }

  login(user: LoginRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(this.API + "/login", user, {withCredentials: true});
  }

  logout() {
    return this.http.post<void>(this.API + "/logout", {}, {withCredentials: true});
  }
  
}
