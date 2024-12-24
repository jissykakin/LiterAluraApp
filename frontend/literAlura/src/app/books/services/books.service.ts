import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { catchError, map, Observable, of } from 'rxjs';
import { environments } from '../../../environments/environments';
import { Book, Language } from '../interfaces/book.interface';
import { Page } from '../interfaces/page.interface';
import { Author } from '../interfaces/author.interface';


@Injectable({providedIn: 'root'})
export class BooksService {

private baseUrl: string = environments.baseURL;

  constructor( private http: HttpClient) { }


  getBooks():Observable<Book[]> {
    return this.http.get<Book[]>( `${ this.baseUrl }/books` );
  }



  getBooksTop10Download():Observable<Book[]> {
    return this.http.get<Book[]>( `${ this.baseUrl }/books/topdownload` );
  }

  getBooksTop10Language(lang:String):Observable<Book[]> {
    return this.http.get<Book[]>( `${ this.baseUrl }/books/lang/${lang}` );
  }


  getBookById( id: number ):Observable<Book | undefined>{
    return this.http.get<Book>( `${ this.baseUrl }/books/${ id }` )
    .pipe(
      catchError( error => of(undefined))
    );
  }

  getSuggestions( query: string ):Observable<Book[]>{
    return this.http.get<Book[]>(`${ this.baseUrl }/books/search?q=${ query }&_limit=6`);
  }

  // getAllBooks( page: number = 1, size:number = 20 ):Observable<Book[]>{
  //   return this.http.get<Book[]>(`${ this.baseUrl }/books?page=${ page }&size= ${ size }`);
  // }

  getAllBooksPaginated(page: number, size: number): Observable<Page<Book>> {
    return this.http.get<Page<Book>>(`${ this.baseUrl }/books?page=${page}&size=${size}`);
  }


  addBook( book: Book ):Observable<Book>{
    return this.http.post<Book>( `${ this.baseUrl }/books`, book );
  }

  updateBook( book: Book ):Observable<Book>{
    if(!book.Id) throw new Error("Hero Id is required");
    return this.http.patch<Book>( `${ this.baseUrl }/books/${book.Id}`, book );
  }

  deleteBookById( id: number ):Observable<boolean>{
    if(!id) throw new Error("Hero Id is required");
    return this.http.delete( `${ this.baseUrl }/books/${ id }`  )
    .pipe(
      map( resp => true),
      catchError( err => of(false))
    );
  }

  getAllAuthorsPaginated(page: number, size: number): Observable<Page<Author>> {
    return this.http.get<Page<Author>>(`${ this.baseUrl }/authors?page=${page}&size=${size}`);
  }





}
