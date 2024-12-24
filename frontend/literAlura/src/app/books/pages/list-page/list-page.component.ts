import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { Book, Lang, Language } from '../../interfaces/book.interface';
import { BooksService } from '../../services/books.service';
import { FormControl } from '@angular/forms';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'books-list-page',
  templateUrl: './list-page.component.html',
  styles: `

    .demo-table {
      width: 100%;
    }

    .mat-mdc-row .mat-mdc-cell {
      border-bottom: 1px solid transparent;
      border-top: 1px solid transparent;
      cursor: pointer;
    }

    .mat-mdc-row:hover .mat-mdc-cell {
      border-color: #ccc;
    }

    .demo-row-is-clicked {
      font-weight: bold;
    }
  `
})
export class ListPageComponent implements  OnInit {


  public books: Book[] = [];
  displayedColumns: string[] = ['title', 'authors', 'language', 'downloadCount', 'verLibro', 'verMas'];
  dataSource = new MatTableDataSource<Book>(this.books);

  pageSize = 10;
  pageIndex = 0;
  totalElements = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;



  constructor(
    private booksService : BooksService
   ){}


  ngOnInit(): void {
    this.getBookPaginated()

  }

  getBookPaginated():void {
    this.booksService.getAllBooksPaginated(this.pageIndex, this.pageSize)
    .subscribe(
        page => {
        this.books = page.content;
        this.totalElements = page.totalElements;
    });
  }

  pageChanged(event: PageEvent) {
    this.pageIndex = event.pageIndex;
    this.pageSize = event.pageSize;
    this.getBookPaginated();
  }




}
