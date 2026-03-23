import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../models/user';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class UserService {
  private http = inject(HttpClient)
  private API = "http://localhost:8080/api/users"
  
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
}
