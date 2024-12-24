package com.challenger.literAlura.repositories;


import com.challenger.literAlura.models.Author;
import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.models.Language;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleContainingIgnoreCase(String nameBook);

    Optional<Book> findOneByTitle(String title);

    @Query("SELECT b FROM Book b JOIN FETCH b.authors WHERE b.language = :language")
    List<Book> findByLanguage(Language language);


    @Query("SELECT b FROM Book b JOIN FETCH b.authors a WHERE a.name ILIKE %:nameAuthor% ORDER BY a.name")
    List<Book> getBookByNameOfAuthor(String nameAuthor);

    @Query("SELECT b FROM Book b JOIN FETCH b.authors ORDER BY b.title")
    List<Book> findAllWithAuthors();


    @Query("SELECT b FROM Book b JOIN FETCH b.authors ORDER BY b.downloadCount DESC LIMIT 10")
    List<Book> findTop10BooksByDownload();

    @Modifying
    @Transactional
    @Query("update Book b set b.subjects = :subjects , b.mediaType = :mediaType , b.downloadCount = :downloadCount" +
            " , b.imageBook = :imageBook , b.viewBook = :viewBook " +
            "where b.title = :title")
    Integer updateBook(@NonNull String title, @NonNull String subjects,@NonNull String mediaType,@NonNull Double downloadCount,@NonNull String imageBook, @NonNull String viewBook);
}
