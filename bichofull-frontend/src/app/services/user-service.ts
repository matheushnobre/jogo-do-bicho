import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { DepositResponseDTO } from '../dto/deposit/deposit-response-dto';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})

export class UserService {
  private http = inject(HttpClient)
  API = `${environment.api_url}/users`
  
  user = signal<User | null>(null);
  
  private userSubject = new BehaviorSubject<User | null>(null);
  user$ = this.userSubject.asObservable();

  constructor(){}

  getProfile(): Observable<User>{
    return this.http.get<User>(this.API + '/me', {withCredentials: true}).pipe(
      tap(user => {
          this.user.set(user), 
          this.userSubject.next(user)
        })
      );
  }

  clearProfile(): void {
    this.user.set(null);
    this.userSubject.next(null);
  }

  refreshProfile(): void {
    this.getProfile().subscribe();
  }

  deposit(value: number): Observable<DepositResponseDTO>{
    return this.http.post<DepositResponseDTO>(this.API + '/deposit', {value: value}, {withCredentials: true})
  }
}
