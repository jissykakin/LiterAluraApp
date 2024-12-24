import { Pipe, PipeTransform } from '@angular/core';

import { Book } from '../interfaces/book.interface';

@Pipe({
  name: 'bookImage'
})
export class BookImagePipe implements PipeTransform {

  transform(book: Book): string {
    if (book.viewBook) return book.viewBook;
    return  'assets/no-image.png';

  }

}
