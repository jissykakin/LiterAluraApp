package com.challenger.literAlura.models;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column( unique = true)
    private  String name;

    private Integer birthYear;

    private Integer deathYear;
    //@ManyToOne


    //private Book book;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    public Author(){}

    public Author(DataAuthor author) {

        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }


    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public Long getId() {
        return Id;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return " name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear ;
    }
}
