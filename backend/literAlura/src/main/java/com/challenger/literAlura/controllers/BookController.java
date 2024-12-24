package com.challenger.literAlura.controllers;


import com.challenger.literAlura.dtos.BookDTO;
import com.challenger.literAlura.models.Book;
import com.challenger.literAlura.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/lang/{language}")
    public  List<BookDTO> getTop10BooksByLanguage(@PathVariable String language){
        return bookService.getTop10BooksByLanguage(language);
    }

    @GetMapping("/{id}")
    public BookDTO getBookById(@PathVariable Long id){
        return bookService.getBookById(id);
    }

    @GetMapping
    public Page<BookDTO> getAllLibros(Pageable pageable) {
        // Ordenar por título de forma ascendente por defecto
        Sort sort = Sort.by(Sort.Direction.ASC, "title");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        System.out.println(pageable);
        return bookService.findAll(pageable);
    }


    @GetMapping("/topdownload")
    public  List<BookDTO> getBooksTopDownload(){
        return bookService.getTop10BooksByDownload();
    }


    @GetMapping("/search")
    public List<BookDTO> searchBooksbyTitle(@RequestParam(value = "q", required = false) String query,
                                             @RequestParam(value = "_limit", defaultValue = "10") Integer limit){
        return bookService.searchBooksbyTitle(query, limit);
    }




}
