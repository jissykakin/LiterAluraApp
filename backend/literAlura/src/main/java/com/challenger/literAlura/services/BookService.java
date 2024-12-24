package com.challenger.literAlura.services;

import com.challenger.literAlura.dtos.BookDTO;

import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.models.Language;
import com.challenger.literAlura.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class BookService {


    @Autowired
    BookRepository bookRepository;


    public  Object insertOrUpdateBook(Book book){
        Book bookToUpdate = bookRepository.findOneByTitle(book.getTitle()).orElse(null);
        if (bookToUpdate == null) {
           return bookRepository.save(book);
        } else {
           bookToUpdate.setDownloadCount(book.getDownloadCount());
           bookToUpdate.setImageBook(book.getImageBook());
           bookToUpdate.setViewBook(book.getViewBook());
           bookToUpdate.setLanguage(book.getLanguage());
           bookToUpdate.setSubjects(book.getSubjects());
           System.out.println("Libro Ya ingresado");
           return bookRepository.save(bookToUpdate);
        }

    }


//    public List<BookDTO> findAll() {
//        List<BookDTO> books = convertToBookDTO(bookRepository.findAllWithAuthors());
//        return books;
//    }

    public Page<BookDTO> findAll(Pageable pageable) {
        Page<Book> books = bookRepository.findAllWithAuthors(pageable);

        System.out.println(books);
        return books.map(this::convertBookToBookDTO);
    }

    public List<Book> findBooksByLanguage(Language language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books;
    }

    public List<Book> getBooksByNameOfAuthor(String nameSearch) {
        List<Book> books = bookRepository.getBookByNameOfAuthor(nameSearch);
        return books;
    }

    public List<BookDTO> getTop10BooksByDownload() {
        List<BookDTO> books = convertToBookDTO(bookRepository.findTop10BooksByDownload());
        return books;
    }


    public BookDTO getBookById(Long id) {
        Optional<Book> resultBook = bookRepository.findById(id);

        if(resultBook.isPresent()){
            Book book =  resultBook.get();
            return convertBookToBookDTO(book);
        }else{
            return null;
        }
    }


    public List<BookDTO> getTop10BooksByLanguage(String lang) {

        Language language =  Language.fromString(lang);
        List<BookDTO> books = convertToBookDTO(bookRepository.findTop10BooksByLanguage(language));
        return books;
    }





    public List<BookDTO> searchBooksbyTitle(String query, Integer limit ) {
        if (query != null) {
            List<BookDTO> books = convertToBookDTO(bookRepository.findByTitleContainingIgnoreCase(query));
            return books;
        }
        return null;
    }


    private List<BookDTO> convertToBookDTO(List<Book> books){
        return books.stream()
                .map(this::convertBookToBookDTO)
                .collect(Collectors.toList());
    }

    private BookDTO convertBookToBookDTO(Book book) {

        return new BookDTO(book.getId(), book.getTitle(), book.getSubjects(), book.getLanguage(),
                book.getMediaType(), book.getDownloadCount(), book.getImageBook(), book.getViewBook(),
                book.getAuthors()
                        .stream()
                        .map(Author::getName)
                        .toList());

    }

}
