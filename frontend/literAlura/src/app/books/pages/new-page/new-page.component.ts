import { Component, OnInit } from '@angular/core';
import { Book, Language, MediaType } from '../../interfaces/book.interface';
import { FormControl, FormGroup } from '@angular/forms';
import { BooksService } from '../../services/books.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { filter, switchMap } from 'rxjs';
import { ConfirmDialogComponent } from '../../components/confirm-dialog/confirm-dialog.component';

@Component({
  selector: 'app-new-page',
  templateUrl: './new-page.component.html',
  styles: ``
})
export class NewPageComponent implements OnInit {

  //formulario rectivo
  public bookForm =   new FormGroup({
    Id:               new FormControl<number>(0),
    title:            new FormControl<string>('', { nonNullable:true }),
    language:         new FormControl<Language>(Language.Ingles),
    subjects:         new FormControl<string>(''),
    mediaType:        new FormControl<MediaType>(MediaType.Text),
    downloadCount:    new FormControl<number>(0),
    viewBook:         new FormControl<string>(''),
  });


  get currentBook(): Book  {
    const book = this.bookForm.value as Book;
    return book;
  }


  public languages = [
    { id:'ALEMAN', value: 'Aleman' },
    { id:'ESPAÑOL', value: 'Español' },
    { id:'FRANCES', value: 'Frances' },
    { id:'INGLES', value: 'Ingles' }
  ];

  constructor(
    private booksService : BooksService,
    private activatedRoute : ActivatedRoute,
    private router: Router,
    private snackbar: MatSnackBar,
    private dialog: MatDialog,
   ){}

  ngOnInit(): void {

    if(!this.router.url.includes('edit')) return;

      this.activatedRoute.params
      .pipe(
        switchMap( ( { id } ) => this.booksService.getBookById( id ) ),
      )
      .subscribe( book => {
          if (!book) return this.router.navigateByUrl( '/' );
          this.bookForm.reset( book )
          return;
      })

    }

  onSubmit():void {
   if ( this.bookForm.invalid ) return;


   if( this.currentBook.Id ){
      this.booksService.updateBook( this.currentBook )
      .subscribe( book => {
        this.showSnackBar(`${ book.title } updated!`)
      });
      return;
   }

   this.booksService.addBook( this.currentBook )
   .subscribe( book => {
    this.showSnackBar(`${ book.title } created!`);
    this.router.navigate(['/books/edit', book.Id])

   });

   return;

  }


  showSnackBar ( message: string ):void{

      this.snackbar.open( message, 'Done', {
        horizontalPosition: 'center',
        verticalPosition:'top',
        duration: 2500,

      } )
  }

  onDeleteBook():void {

    if(!this.currentBook.Id) throw new Error("Book id is required");

    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '350px',
      data: this.bookForm.value
    });

    dialogRef.afterClosed()
    .pipe(
      filter( (result: boolean) => result ),
      switchMap( () =>  this.booksService.deleteBookById( this.currentBook.Id ) ),
      filter( (wasDeleted: boolean) => wasDeleted ),
    )
    .subscribe( () => {
      this.showSnackBar(`${ this.currentBook.title } Deleted!`);
      this.router.navigate(['/books/list'])
    });



    // dialogRef.afterClosed().subscribe( result => {

    //   if(!result) return;

    //   this.heroesService.deleteHeroById( this.currentHero.id )
    //   .subscribe( wasDeleted => {
    //     if( wasDeleted ){
    //       this.showSnackBar(`${ this.currentHero.superhero } Deleted!`);
    //       this.router.navigate(['/heroes/list'])
    //     }
    //   });


    // });

  }
}
