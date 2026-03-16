import { Component, inject } from '@angular/core';
import { FormGroup, FormControl, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterModule } from '@angular/router';
import { RegisterRequest } from '../../../dto/auth/register-request';
import { RegisterService } from '../../../services/register-service';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterModule  
  ],
  templateUrl: './register-component.html',
  styleUrl: './register-component.scss',
})

export class RegisterComponent {
  private registerService = inject(RegisterService);
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  registerForm = new FormGroup({
    name: new FormControl('', {nonNullable: true, validators: [Validators.required]}),
    email: new FormControl('', {nonNullable: true, validators: [Validators.required, Validators.email]}),
    password: new FormControl('', {nonNullable: true, validators: [Validators.required, Validators.minLength(6)]})
  });

  register(){
    if(this.registerForm.invalid) return;

    const user: RegisterRequest = this.registerForm.getRawValue();
    this.registerService.register(user).subscribe({
      next: (response) => {
        this.snackBar.open(
          "Conta criada com sucesso!",
          "OK",
          { duration: 3000, horizontalPosition: 'center', verticalPosition: 'top' }
        );

        this.router.navigate(['/login'])
      },
      error: (err) => {
        if(err.status == 409){
          this.registerForm.get('email')?.setErrors({duplicated: true});
        } 
        
        else{
          this.snackBar.open(
            "Erro inesperado. Tente novamente.",
            "Fechar",
            { duration: 3000, horizontalPosition: 'center', verticalPosition: 'top' }
          );
        }
      }
    })
  }
}
