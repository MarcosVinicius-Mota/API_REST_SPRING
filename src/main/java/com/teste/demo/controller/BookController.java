package com.teste.demo.controller;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.services.BookService;
import com.teste.demo.util.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {


    @SuppressWarnings("FieldMayBeFinal")
    private BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON)
    public List<BookDTO> findAll(){
        return service.findAll();
    }

}
