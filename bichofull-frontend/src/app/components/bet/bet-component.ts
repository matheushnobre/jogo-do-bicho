import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-bet',
  imports: [CommonModule, FormsModule],
  templateUrl: './bet-component.html',
  styleUrl: './bet-component.scss',
})

export class BetComponent {
  selectedType: string = 'group';
  betNumber: string = '';
  betAmount: number | null = null;

  setType(type: string) {
    this.selectedType = type;
  }

  setAmount(val: number) {
    this.betAmount = val;
  }

  confirmBet() {
    console.log('Aposta confirmada:', {
      tipo: this.selectedType,
      numero: this.betNumber,
      valor: this.betAmount
    });
  }
}