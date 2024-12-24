import { Component, OnInit } from '@angular/core';
import { Book, Lang } from '../../interfaces/book.interface';
import { BooksService } from '../../services/books.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styles: ``
})
export class HomePageComponent implements OnInit {

   // selectedValue = new FormControl('es');
    public selectedValue: string ="es";
    public booksTopLang: Book[] = [];
    public booksTopDownload: Book[] = [];

    langs: Lang[] = [
      {value: 'es', lang: 'ESPAÑOL'},
      {value: 'en', lang: 'INGLES'},
      {value: 'tl', lang: 'FILIPINO'},
      {value: 'fr', lang: 'FRANCES'},
      {value: 'de', lang: 'ALEMAN'},
      {value: 'pt', lang: 'PORTUGUES'},
    ];


    constructor( private booksService : BooksService ){}


    ngOnInit(): void {
      this.goSearchLanguage();

      this.booksService.getBooksTop10Download()
        .subscribe( books => this.booksTopDownload = books);

    }

    goSearchLanguage():void {
      console.log("probando",this.selectedValue);
        this.booksService.getBooksTop10Language(this.selectedValue)
        .subscribe( books => this.booksTopLang = books);
    }

}
