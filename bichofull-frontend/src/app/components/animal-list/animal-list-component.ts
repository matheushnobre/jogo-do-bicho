import { Component, inject } from '@angular/core';
import { AnimalService } from '../../services/animal-service';
import { Animal } from '../../models/animal';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { AnimalComponent } from '../animal/animal-component';

@Component({
  selector: 'app-animal-list',
  standalone: true,
  imports: [CommonModule, AnimalComponent],
  templateUrl: './animal-list-component.html',
  styleUrl: './animal-list-component.scss',
})

export class AnimalListComponent {
  private animalService = inject(AnimalService)
  
  animals$: Observable<Animal[]> = this.animalService.findAll();
}
