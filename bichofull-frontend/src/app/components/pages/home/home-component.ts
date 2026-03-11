import { Component } from '@angular/core';
import { AnimalListComponent } from '../../animal-list/animal-list-component';

@Component({
  selector: 'app-home',
  imports: [AnimalListComponent],
  templateUrl: './home-component.html',
  styleUrl: './home-component.scss',
})

export class HomeComponent {

}
