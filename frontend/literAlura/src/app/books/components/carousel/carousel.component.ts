import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../../interfaces/book.interface';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrl: './carousel.component.css'
})
export class CarouselComponent implements OnInit{

    @Input()
    public books!: Book[] ;

    librosPorPagina = 5;
    paginas: Book[][] = [];


    ngOnInit(): void {
      if ( !this.books ) throw new Error("Hero property is required");
    }

    paginarLibros() {
      this.paginas = [];
      for (let i = 0; i < this.books.length; i += this.librosPorPagina) {
        this.paginas.push(this.books.slice(i, i + this.librosPorPagina));
        console.log(i)
      }
    }

    ngOnChanges() {
      this.paginarLibros();
    }



}
