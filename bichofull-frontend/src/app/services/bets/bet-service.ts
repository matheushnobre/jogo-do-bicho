import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BetPost } from '../../dto/bets/bet-post';
import { BetResult } from '../../dto/bets/bet-result';
import { Observable } from 'rxjs';
import { HistoryBetDTO } from '../../dto/bets/history-bet-dto';

@Injectable({
  providedIn: 'root',
})

export class BetService {
  http = inject(HttpClient)
  
  API = "http://localhost:8080/api/bets"

  constructor(){}

  placeBet(betPost: BetPost): Observable<BetResult>{
    return this.http.post<BetResult>(this.API + '/bet', betPost, {withCredentials: true});
  }

  getHistory(page: number): Observable<HistoryBetDTO>{
    return this.http.get<HistoryBetDTO>(this.API + '/my-bets' + `?page=${page}`, {withCredentials: true})
  }
  
}
