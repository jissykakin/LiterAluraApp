package com.challenger.literAlura.models;

import jakarta.persistence.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

   @Column(unique = true)
    private String title;

    private String subjects;

    @Enumerated(EnumType.STRING)
    private Language language;

    private String mediaType;

    private Double downloadCount;

    private String imageBook;

    private String viewBook;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id" , referencedColumnName = "id") )
    private Set<Author> authors = new HashSet<>();
    //@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //private List<Author> authors;

    public Book() {}

    public Book( DataBook databook) {
        this.title = databook.title();
        this.subjects = databook.subjects().get(0);
        this.language = Language.fromString(databook.languages().get(0).trim());
        this.mediaType = databook.mediaType();
        this.downloadCount = databook.downloadCount();
        this.imageBook = databook.formats().get("text/html");
        this.viewBook = databook.formats().get("image/jpeg");
    }

    @Override
    public String toString() {
        return  " title='" + title + '\'' +
                ", subjects='" + subjects + '\'' +
                ", authors=" + authors +
                ", languages=" + language +
                ", mediaType='" + mediaType + '\'' +
                ", downloadCount=" + downloadCount +
                ", imageBook =" + imageBook +
                ", viewBook =" + viewBook;
    }

    public Long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubjects() {
        return subjects;
    }

    //public  void getAuthors(List<Author> authors) {
    //    authors.forEach(e -> e.setBook(this));
   //     this.authors = authors;
    //}

    public Set<Author> getAuthors() {
        return Collections.unmodifiableSet(authors);
        // Devuelve una vista inmutable para evitar modificaciones directas
    }

    public void setId(Long id) {
        Id = id;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Language getLanguage() {
        return language;
    }

    public String getMediaType() {
        return mediaType;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public String getImageBook() {
        return imageBook;
    }

    public String getViewBook() {
        return viewBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }


    public void setLanguage(Language languages) {
        this.language = languages;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public void setViewBook(String viewBook) {
        this.viewBook = viewBook;
    }
}


