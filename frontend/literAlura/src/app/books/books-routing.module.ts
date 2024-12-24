import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BookPageComponent } from './pages/book-page/book-page.component';
import { ListPageComponent } from './pages/list-page/list-page.component';
import { LayoutPageComponent } from './pages/layout-page/layout-page.component';
import { NewPageComponent } from './pages/new-page/new-page.component';
import { SearchPageComponent } from './pages/search-page/search-page.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { AuthorsPageComponent } from './pages/authors-page/authors-page.component';

const routes: Routes = [
  {
    path: '',
    component: LayoutPageComponent,
    children: [
      { path: 'new-book', component: NewPageComponent },
      { path: 'search', component: SearchPageComponent },
      { path: 'edit/:id', component: NewPageComponent },
      { path: 'home', component: HomePageComponent },
      { path: 'authors', component: AuthorsPageComponent},
      { path: 'list', component: ListPageComponent },
      { path: ':id', component: BookPageComponent },
      { path: '**', redirectTo: 'home' }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BooksRoutingModule { }




