import { CanActivateFn, Router } from '@angular/router';
import { catchError, map, of } from 'rxjs';
import { UserService } from '../services/user-service';
import { inject } from '@angular/core';

export const authGuard: CanActivateFn = () => {

  const userService = inject(UserService);
  const router = inject(Router);

  return userService.getProfile().pipe(
    map(() => true),
    catchError(() => {
      router.navigate(['/login']);
      return of(false);
    })
  );

};