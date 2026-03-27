import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { RegisterRequest } from '../dto/auth/register-request';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class RegisterService {
  http = inject(HttpClient)
  API = `${environment.api_url}/auth`
  
  constructor(){}

  register(user: RegisterRequest): Observable<User>{
    return this.http.post<User>(this.API + '/register', user);
  }
}
