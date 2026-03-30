import { Component, inject } from '@angular/core';
import { Router, RouterLinkActive, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login-service';
import { UserService } from '../../services/user-service';

@Component({
  selector: 'app-nav',
  imports: [RouterModule, RouterLinkActive],
  standalone: true,
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss',
})

export class NavComponent {

  authService = inject(LoginService);
  userService = inject(UserService);
  
  router = inject(Router);

  logout(){
      this.authService.logout().subscribe(() => {
        this.userService.clearProfile();
        this.router.navigate(['/login']);
      });
  }

  isLoggedUser(): boolean {
    return !!this.userService.user();
  }
}
