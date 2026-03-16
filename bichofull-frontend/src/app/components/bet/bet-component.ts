import { ChangeDetectorRef, Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BetService } from '../../services/bets/bet-service';
import { Injectable, inject } from '@angular/core';
import { BetResult } from '../../dto/bets/bet-result';
import { BetPost } from '../../dto/bets/bet-post';
import { firstValueFrom, Observable } from 'rxjs';

@Component({
  selector: 'app-bet',
  imports: [CommonModule, FormsModule],
  templateUrl: './bet-component.html',
  styleUrl: './bet-component.scss',
})

export class BetComponent {
  betType: string = 'GROUP';
  betNumber: string = '';
  betAmount: number | null = null;
  numberErrorMessage: string = '';
  amountErrorMessage: string = '';
  betPost: BetPost = new BetPost();
  betResult: BetResult | null = null;
  showModal: boolean = false;
  isLoading: boolean = false;

  private betService = inject(BetService);
  private cdr = inject(ChangeDetectorRef);

  private validationRules: any = {
    GROUP: { min: 1, max: 25, label: 'BICHO' },
    TENS: { min: 0, max: 99, label: 'CENTENA' },
    THOUSANDS: { min: 0, max: 9999, label: 'MILHAR' }
  };

  private validateBetNumber(){
    const num = parseInt(this.betNumber);
    const rule = this.validationRules[this.betType];

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
    this.betType = type;
    this.validateInput();
  }

  setAmount(val: number) {
    this.betAmount = val;
    this.validateInput();
  }

  async confirmBet() {
    this.validateInput();
    if(this.numberErrorMessage || this.amountErrorMessage || !this.betAmount){
      return;
    }
    
    this.isLoading = true;
    this.showModal = false;
    this.betResult = null;
    this.cdr.detectChanges();

    this.betPost = {
      betNumber: Number(this.betNumber),
      betAmount: Number(this.betAmount),
      betType: this.betType
    };
    
    try{
      const response = await firstValueFrom(this.betService.placeBet(this.betPost));
      this.betResult = response;
      this.showModal = true;
    }

    catch(error){
      console.log('Erro na aposta', error);
      alert('Falha ao processar aposta. Tente novamente!')
    }

    finally {
      this.isLoading = false;
      this.cdr.detectChanges();
    }
  }

  closeModal() {
    this.showModal = false;
    this.betResult = null;
  }
}