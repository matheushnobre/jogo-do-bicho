import { Component, inject, OnInit, Signal } from '@angular/core';
import { NavComponent } from '../../nav/nav.component';
import { UserService } from '../../../services/user-service';
import { BalanceComponent } from '../../balance/balance.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-my-account.component',
  imports: [NavComponent, BalanceComponent],
  templateUrl: './my-account.component.html',
  styleUrl: './my-account.component.scss',
})


export class MyAccountComponent implements OnInit{
  private userService = inject(UserService);
  user = this.userService.user;

  constructor(private dialog: MatDialog){}

  ngOnInit() {
    this.userService.refreshProfile();
  }
}
