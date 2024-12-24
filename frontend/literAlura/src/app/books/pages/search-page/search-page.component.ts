import { Component } from '@angular/core';
import { Book } from '../../interfaces/book.interface';
import { FormControl } from '@angular/forms';
import { BooksService } from '../../services/books.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';


@Component({
  selector: 'app-search-page',
  templateUrl: './search-page.component.html',
  styles: ``
})
export class SearchPageComponent {

  public books: Book[] = [];
  public searchInput = new FormControl('');
  public selectedBook?: Book;

  constructor(private booksService: BooksService ){}

  searchBook(){
    const value: string = this.searchInput.value || '';
    if(!value) return;

    this.booksService.getSuggestions( value )
    .subscribe( books => this.books = books );

  }


  onSelectedOption( event : MatAutocompleteSelectedEvent ):void {
    if( !event.option.value ){
      this.selectedBook = undefined;
      return;
    }

    const book: Book = event.option.value;
    this.searchInput.setValue( book.title );
    this.selectedBook = book;
  }


}
