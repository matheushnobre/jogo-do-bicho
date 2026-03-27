import { Component, inject, OnInit } from '@angular/core';
import { BetService } from '../../../services/bets/bet-service';
import { BehaviorSubject, Observable, switchMap } from 'rxjs';
import { CommonModule } from '@angular/common';
import { NavComponent } from '../../nav/nav.component';
import { HistoryBetDTO } from '../../../dto/bets/history-bet-dto';

@Component({
  selector: 'app-history-bets-component',
  imports: [CommonModule, NavComponent],
  templateUrl: './history-bets-component.html',
  styleUrl: './history-bets-component.scss',
})

export class HistoryBetsComponent implements OnInit {
  private betService = inject(BetService);
  isLoading: boolean = false;
  currentPage = new BehaviorSubject<number>(0);
  bets$: Observable<HistoryBetDTO> = this.currentPage.pipe(
    switchMap(page => this.betService.getHistory(page))
  );

  ngOnInit() {
  }

  nextPage(totalPages: number){
    if(this.currentPage.value < totalPages - 1){
      this.currentPage.next(this.currentPage.value + 1);
    }
  }

  prevPage(){
    if(this.currentPage.value > 0){
      this.currentPage.next(this.currentPage.value - 1);
    }
  }

  getTranslation(type: string | undefined | null): string {
    if(!type) return '-';

    const translations: { [key: string]: string } = {
      'group': 'Bicho',
      'tens': 'Dezena',
      'hundreds': 'Centena',
      'thousands': 'Milhar'
    };

    const key = type.toLowerCase();
    return translations[key] || type;
  }
}

