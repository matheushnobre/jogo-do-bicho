import { ChangeDetectorRef, Component } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { LoginService } from '../../../services/login-service';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { LoginRequest } from '../../../dto/auth/login-request';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    RouterModule
  ],
  templateUrl: './login-component.html',
  styleUrl: './login-component.scss',
})

export class LoginComponent {
    private loginService = inject(LoginService);
    private router = inject(Router);
    private cdr = inject(ChangeDetectorRef);
    
    loginForm = new FormGroup({
      email: new FormControl('', { nonNullable: true, validators: [Validators.required, Validators.email] }),
      password: new FormControl('', { nonNullable: true, validators: [Validators.required] })
    });

    loginError: boolean = false;

    login(){
      if (this.loginForm.invalid) return;

      this.loginError = false;

      const user: LoginRequest = this.loginForm.getRawValue();
      this.loginService.login(user).subscribe({
        next: (response) => {
          localStorage.setItem('token', response.token);
          this.router.navigate(['/home']);
        },
        error: (err) => {
          this.loginError = true;
          this.cdr.detectChanges();
        }
      });
    }
}