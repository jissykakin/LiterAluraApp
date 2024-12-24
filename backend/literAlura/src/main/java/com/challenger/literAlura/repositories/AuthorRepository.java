package com.challenger.literAlura.repositories;

import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Book;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Fetch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {


    @Query("SELECT a FROM Author a JOIN FETCH a.books ORDER BY a.id")
    Page<Author> findAllWithBooks(Pageable pageable);

//    @Query("SELECT a FROM Author a JOIN FETCH a.books ORDER BY a.name")
//    List<Author> findAllWithBooks();



    Optional<Author> findOneByName(String name );


    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.birthYear <=:yearSearch AND a.deathYear >= :yearSearch")
    List<Author> getAuthorLivedInRangeYear(Integer yearSearch);


    @Query("SELECT a FROM Author a JOIN FETCH a.books WHERE a.name ILIKE %:nameAuthor% ORDER BY a.name")
    List<Author> getAuthorByName(String nameAuthor);


    @Modifying
    @Transactional
    @Query("update Author a set a.birthYear = :birthYear, a.deathYear = :deathYear where a.name = :name")
    Integer updateAuthor(@NonNull Integer birthYear, @NonNull Integer deathYear, @NonNull String name);


}
