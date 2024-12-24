import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../../interfaces/book.interface';

@Component({
  selector: 'books-book-card',
  templateUrl: './card.component.html',
  styles: `

    .mat-card {
        width: 240px;
        min-height: 450px;
        margin: 10px;


      }

    .mat-card-image {
      width: 100%; /* Que la imagen ocupe todo el ancho de la tarjeta */
      height: 200px; /* Ajusta la altura según tus necesidades */
      object-fit: cover; /* Hace que la imagen se ajuste al contenedor manteniendo la proporción */
    }

    .mat-card-actions {
      position: absolute;
      bottom: 0;
      left: 0;
      right: 0;
      padding: 16px;

    }
  `
})
export class CardComponent implements OnInit {
  @Input()
  public book!: Book;

  ngOnInit(): void {
    if ( !this.book ) throw new Error("Hero property is required");
  }


}
