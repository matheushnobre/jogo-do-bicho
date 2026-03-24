import { Component, inject } from '@angular/core';
import { BetService } from '../../../services/bets/bet-service';
import { Observable } from 'rxjs';
import { BetResult } from '../../../dto/bets/bet-result';
import { CommonModule } from '@angular/common';
import { NavComponent } from '../../nav/nav.component';

@Component({
  selector: 'app-history-bets-component',
  imports: [CommonModule, NavComponent],
  templateUrl: './history-bets-component.html',
  styleUrl: './history-bets-component.scss',
})

export class HistoryBetsComponent {
  private betService = inject(BetService);
  bets$: Observable<BetResult[]> = this.betService.getHistory();

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

