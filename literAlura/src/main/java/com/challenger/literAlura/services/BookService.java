package com.challenger.literAlura.services;

import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.models.Language;
import com.challenger.literAlura.repositories.BookRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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


    public List<Book> findAll() {
        List<Book> books = bookRepository.findAllWithAuthors();
        return books;
    }

    public List<Book> findBooksByLanguage(Language language) {
        List<Book> books = bookRepository.findByLanguage(language);
        return books;
    }

    public List<Book> getBooksByNameOfAuthor(String nameSearch) {
        List<Book> books = bookRepository.getBookByNameOfAuthor(nameSearch);
        return books;
    }

    public List<Book> getTop10BooksByDownload() {
        List<Book> books = bookRepository.findTop10BooksByDownload();
        return books;
    }
}
