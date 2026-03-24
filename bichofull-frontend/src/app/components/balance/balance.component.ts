import { ChangeDetectorRef, Component, inject, Input } from '@angular/core';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-balance',
  imports: [CommonModule, FormsModule],
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.scss',
})

export class BalanceComponent {
  @Input()
  loggedUser?: User;
  depositOk: boolean = false;

  private cdr = inject(ChangeDetectorRef);

  private userService = inject(UserService);
  depositValue = 0
  
  deposit(){
    console.log('clicado');
    this.userService.deposit(this.depositValue).subscribe({
      next: (response) => {
        this.userService.refreshProfile();
        this.depositValue = 0;
        this.depositOk = true;
        this.cdr.detectChanges();
      },
      error: (err) => {
        alert("Erro ao depositar");
        console.log('erro ', err);
      } 
    });
    
  }

  closeAlert(){
    this.depositOk = false;
  }
}
