package com.teste.demo.controller;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.services.BookService;
import com.teste.demo.util.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
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

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON)
    public BookDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public BookDTO createBook(@RequestBody BookDTO bookDTO){
        return service.createBook(bookDTO);
    }

    @PutMapping
    public BookDTO updateBook(@RequestBody BookDTO bookDTO){
        return service.update(bookDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
