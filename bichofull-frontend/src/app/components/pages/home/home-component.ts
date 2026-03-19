import { Component } from '@angular/core';
import { AnimalListComponent } from '../../animal-list/animal-list-component';
import { BetComponent } from '../../bet/bet-component';
import { Animal } from '../../../models/animal';

@Component({
  selector: 'app-home',
  imports: [AnimalListComponent, BetComponent],
  templateUrl: './home-component.html',
  styleUrl: './home-component.scss',
})

export class HomeComponent {
  selectedAnimalChild?: Animal;

  handleAnimalSelection(animal: Animal){
    this.selectedAnimalChild = animal;
  }
}
