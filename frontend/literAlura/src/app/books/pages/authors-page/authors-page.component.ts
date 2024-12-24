import { Component, OnInit } from '@angular/core';
import { Author } from '../../interfaces/author.interface';
import { MatTableDataSource } from '@angular/material/table';
import { BooksService } from '../../services/books.service';
import { PageEvent } from '@angular/material/paginator';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
  selector: 'app-authors-page',
  templateUrl: './authors-page.component.html',
  styles: ``,
  animations: [
    trigger('detailExpand', [
      state('collapsed,void', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class AuthorsPageComponent  implements OnInit{


  public authors: Author[] = [];
  displayedColumns: string[] = ['Id', 'name', 'birthYear', 'deathYear', 'books'];

  pageSize = 10;
  pageIndex = 0;
  totalElements = 0;


  constructor(
    private booksService : BooksService
   ){}


  ngOnInit(): void {
    this.getAuthorPaginated()

  }

  getAuthorPaginated():void {
    this.booksService.getAllAuthorsPaginated(this.pageIndex, this.pageSize)
    .subscribe(
        page => {
        this.authors = page.content;
        this.totalElements = page.totalElements;
    });

  }

  pageChanged(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getAuthorPaginated();
  }


}
