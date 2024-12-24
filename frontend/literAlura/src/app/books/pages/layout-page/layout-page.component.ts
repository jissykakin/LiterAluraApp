import { Component } from '@angular/core';
import { AuthService } from '../../../auth/services/auth.service';
import { Router } from '@angular/router';
import { User } from '../../../auth/interfaces/user.interface';

@Component({
  selector: 'books-layout-page',
  templateUrl: './layout-page.component.html',
  styles: ``
})
export class LayoutPageComponent {
    public sidebarItems = [
      { label: 'Inicio', icon: 'home', url:'./home'},
      { label: 'Listado Libros', icon: 'label', url:'./list'},
      { label: 'LIstado Autores', icon: 'list', url:'./authors'},
      { label: 'Buscar Libros', icon: 'search', url:'./search'},

    ];

    constructor(
      private authService: AuthService,
      private router: Router
    ){}

    get user():User | undefined {
      return this.authService.currentUser;
    }

    onLogout():void {
      this.authService.logout();
      this.router.navigate(['/auth/login'])
    }

}
