import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { DepositResponseDTO } from '../dto/deposit/deposit-response-dto';
import { environment } from '../../enviroments/enviroment';

@Injectable({
  providedIn: 'root',
})

export class UserService {
  private http = inject(HttpClient)
  API = `${environment.api_url}/users`
  
  
  private userSubject = new BehaviorSubject<User | null>(null);
  user$ = this.userSubject.asObservable();

  constructor(){}

  getProfile(): Observable<User>{
    return this.http.get<User>(this.API + '/me', {withCredentials: true}).pipe(
      tap(user => this.userSubject.next(user))
    );
  }

  refreshProfile(): void {
    this.getProfile().subscribe();
  }

  deposit(value: number): Observable<DepositResponseDTO>{
    return this.http.post<DepositResponseDTO>(this.API + '/deposit', {value: value}, {withCredentials: true})
  }
}
