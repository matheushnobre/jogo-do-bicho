import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { AnimalListComponent } from '../../animal-list/animal-list-component';
import { BetComponent } from '../../bet/bet-component';
import { Animal } from '../../../models/animal';
import { BalanceComponent } from '../../balance/balance.component';
import { UserService } from '../../../services/user-service';
import { inject } from '@angular/core';
import { User } from '../../../models/user';
import { NavComponent } from '../../nav/nav.component';

@Component({
  selector: 'app-home',
  imports: [NavComponent, BalanceComponent, AnimalListComponent, BetComponent],
  templateUrl: './home-component.html',
  styleUrl: './home-component.scss',
})

export class HomeComponent implements OnInit{
  selectedAnimalChild?: Animal;
  private userService = inject(UserService);
  loggedUser?: User;
  private cdr = inject(ChangeDetectorRef);

  ngOnInit(): void {
    this.userService.user$.subscribe(user => {
      if(user) {
        this.loggedUser = user;
        this.cdr.detectChanges();
      }
    });

    this.userService.refreshProfile();
  }

  handleAnimalSelection(animal: Animal){
    this.selectedAnimalChild = animal;
  }

  handleBetNumberManualChange(id: number){
    if(id > 0 && id <= 25){
      this.selectedAnimalChild = {id: id} as Animal;
    } else{
      this.selectedAnimalChild = undefined;
    }
  }
}
