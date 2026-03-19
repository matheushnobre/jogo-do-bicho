import { Component, EventEmitter } from '@angular/core';
import { Animal } from '../../models/animal';
import { Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-animal',
  imports: [CommonModule],
  standalone: true,
  templateUrl: './animal-component.html',
  styleUrl: './animal-component.scss',
})

export class AnimalComponent {
  @Input() animal!: Animal;
  @Output() selected = new EventEmitter<Animal>();
  @Input() isActualSelected: boolean = false;
  
  selectThisAnimal() {
    this.selected.emit(this.animal);
  }
}
