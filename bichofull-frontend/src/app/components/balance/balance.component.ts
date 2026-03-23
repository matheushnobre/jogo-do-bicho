import { Component, inject, Input } from '@angular/core';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';
import { UserService } from '../../services/user-service';

@Component({
  selector: 'app-balance',
  imports: [CommonModule],
  templateUrl: './balance.component.html',
  styleUrl: './balance.component.scss',
})

export class BalanceComponent {
  @Input()
  loggedUser?: User;

  private userService = inject(UserService);
}
