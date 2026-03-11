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
  numberErrorMessage: string = '';
  amountErrorMessage: string = '';

  private validationRules: any = {
    group: { min: 1, max: 25, label: 'BICHO' },
    dezena: { min: 0, max: 99, label: 'CENTENA' },
    thousands: { min: 0, max: 9999, label: 'MILHAR' }
  };

  private validateBetNumber(){
    const num = parseInt(this.betNumber);
    const rule = this.validationRules[this.selectedType];

    if(!this.betNumber){
      this.numberErrorMessage = '';
      return;
    }

    if(isNaN(num) || num < rule.min || num > rule.max){
      this.numberErrorMessage = `O número para jogar ${rule.label} deve ser entre ${rule.min} e ${rule.max}`;
    } else{
      this.numberErrorMessage = '';
    }
  }

  private validateAmountNumber(){
    const num = this.betAmount;
    if (num === null || num === undefined) {
      this.amountErrorMessage = '';
      return;
    }

    if(num <= 0){
      this.amountErrorMessage = 'Valor apostado deve ser maior que 0'
    }
  }

  validateInput(){
    this.validateBetNumber();
    this.validateAmountNumber();
  }

  setType(type: string) {
    this.selectedType = type;
    this.validateInput();
  }

  setAmount(val: number) {
    this.betAmount = val;
    this.validateInput();
  }

  confirmBet() {
    this.validateInput();
    console.log('Aposta confirmada:', {
      tipo: this.selectedType,
      numero: this.betNumber,
      valor: this.betAmount
    });
  }
}