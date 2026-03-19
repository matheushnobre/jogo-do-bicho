import { Component, EventEmitter, inject } from '@angular/core';
import { AnimalService } from '../../services/animal-service';
import { Animal } from '../../models/animal';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';
import { AnimalComponent } from '../animal/animal-component';
import { Output } from '@angular/core';

@Component({
  selector: 'app-animal-list',
  standalone: true,
  imports: [CommonModule, AnimalComponent],
  templateUrl: './animal-list-component.html',
  styleUrl: './animal-list-component.scss',
})

export class AnimalListComponent {
  @Output() selectedAnimal = new EventEmitter<Animal>();
  currentSelectedId?: number;

  private animalService = inject(AnimalService)
  
  animals$: Observable<Animal[]> = this.animalService.findAll();

  onAnimalSelect(animal: any){
    this.currentSelectedId = animal.id;
    this.selectedAnimal.emit(animal);
  }
}
