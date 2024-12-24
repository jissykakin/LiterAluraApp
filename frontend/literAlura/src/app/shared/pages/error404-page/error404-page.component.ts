import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-error404-page',
  templateUrl: './error404-page.component.html',
  styles: `

#layoutError {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}
#layoutError #layoutError_content {
  min-width: 0;
  flex-grow: 1;
}
#layoutError #layoutError_footer {
  min-width: 0;
}

.img-error {
  max-width: 20rem;
}

.lead {
  font-size: 1.25rem;
  font-weight: 300;
}

`
})
export class Error404PageComponent {
  constructor(

    private router: Router
   ){}
  goBack():void {
    this.router.navigateByUrl('/books/list')
  }

}
