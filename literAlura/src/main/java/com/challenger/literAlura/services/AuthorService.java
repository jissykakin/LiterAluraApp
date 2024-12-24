package com.challenger.literAlura.services;

import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.repositories.AuthorRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Author insertOrUpdateAuthor(Author author){

        Author authorToUpdate = authorRepository.findOneByName(author.getName()).orElse(null);
        //Author authorToUpdate = authorRepository.findByName("Shelley, Mary Wollstonecraft");

        if (authorToUpdate == null) {

            return authorRepository.save(author);
           // System.out.println(newAuthor);
            //return newAuthor;
        } else {

            authorToUpdate.setBirthYear(author.getBirthYear());
            authorToUpdate.setDeathYear(author.getDeathYear());

            System.out.println("Autor ya Ingresado");
            return authorRepository.save(authorToUpdate);

        }
    }

    public List<Author> findAll() {
        List<Author> authors = authorRepository.findAllWithBooks();

        return authors;
    }

    public List<Author> findAuthorByRangeOfYear(Integer yearSearch) {
        List<Author> authors = authorRepository.getAuthorLivedInRangeYear(yearSearch);
        return authors;
    }


    public List<Author> getAuthorByName(String nameSearch) {
        List<Author> authors = authorRepository.getAuthorByName(nameSearch);
        return authors;
    }
}
