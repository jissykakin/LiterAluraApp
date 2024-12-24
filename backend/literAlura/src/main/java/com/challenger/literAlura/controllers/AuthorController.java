package com.challenger.literAlura.controllers;

import com.challenger.literAlura.dtos.AuthorDTO;
import com.challenger.literAlura.dtos.BookDTO;
import com.challenger.literAlura.services.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public Page<AuthorDTO> getAllAuthor(Pageable pageable) {
        // Ordenar por título de forma ascendente por defecto
        Sort sort = Sort.by(Sort.Direction.ASC, "name");
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);
        return authorService.findAll(pageable);
    }



}
