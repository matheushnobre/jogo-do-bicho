import { Component, inject } from '@angular/core';
import { Router, RouterLinkActive, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login-service';

@Component({
  selector: 'app-nav',
  imports: [RouterModule, RouterLinkActive],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.scss',
})

export class NavComponent {

  authService = inject(LoginService);
  router = inject(Router);

  logout(){
      this.authService.logout().subscribe(() => {
        this.router.navigate(['/login']);
      });
  }
}
