import { Routes } from '@angular/router';
import { LoginComponent } from './components/pages/login/login-component';
import { RegisterComponent } from './components/pages/register/register-component';
import { HomeComponent } from './components/pages/home/home-component';
import { authGuard } from './guards/auth-guard';

export const routes: Routes = [
    { path: "login", component: LoginComponent},
    { path: "register", component: RegisterComponent},
    { path: "home", component: HomeComponent, canActivate: [authGuard]},
    { path: "**", redirectTo: "home" },

];
