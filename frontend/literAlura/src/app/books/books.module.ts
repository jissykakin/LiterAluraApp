import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';

import { MaterialModule } from '../material/material.module';

import { CardComponent } from './components/card/card.component';

import { ConfirmDialogComponent } from './components/confirm-dialog/confirm-dialog.component';
import { BooksRoutingModule } from './books-routing.module';
import { BookImagePipe } from './pipes/book-image.pipe';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { CarouselComponent } from './components/carousel/carousel.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthorsPageComponent } from './pages/authors-page/authors-page.component';



@NgModule({
  declarations: [
    CardComponent,
    ConfirmDialogComponent,
    BookPageComponent,
    LayoutPageComponent,
    ListPageComponent,
    NewPageComponent,
    SearchPageComponent,
    CarouselComponent,

    //pipes
      BookImagePipe,
      HomePageComponent,
      AuthorsPageComponent,
     


  ],
  imports: [
    CommonModule,
    BooksRoutingModule,
    MaterialModule,
    ReactiveFormsModule,
  ]
})
export class BooksModule { }
