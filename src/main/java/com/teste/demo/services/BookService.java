package com.teste.demo.services;

import com.teste.demo.Repository.BookRepository;
import com.teste.demo.controller.BookController;
import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.exceptions.handler.RequiredObjectInNullException;
import com.teste.demo.exceptions.handler.ResourceNotFoundException;
import com.teste.demo.mapper.BookMapper;
import com.teste.demo.model.Book;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @SuppressWarnings("FieldMayBeFinal")
    private BookMapper mapper;

    @SuppressWarnings("FieldMayBeFinal")
    private BookRepository repository;

    public BookService(BookMapper mapper, BookRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public List<BookDTO> findAll(){
        List<BookDTO> list = mapper.convertToDTOList(repository.findAll());

        list.forEach( (book) -> {
                    book.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(book.getKey())).withSelfRel());
                }
        );

        return list;
    }

    public BookDTO findById(Long id){
        BookDTO result = mapper.convertToDTO(repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found")));
        result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(id)).withSelfRel());
        return result;
    }

    public BookDTO createBook(BookDTO BookDTO){

        if(BookDTO == null){
            throw new RequiredObjectInNullException();
        }

        Book entity = mapper.convertToBook(BookDTO);
        entity = repository.save(entity);

        BookDTO result = mapper.convertToDTO(entity);
        result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(result.getKey())).withSelfRel());
        return result;
    }

    public BookDTO update(BookDTO bookDTO) {

        if(bookDTO == null){
            throw new RequiredObjectInNullException();
        }

        Book book = repository.findById(bookDTO.getKey()).orElseThrow(() -> new ResourceNotFoundException("Not Found"));

        book.setTitle(bookDTO.getTitle());
        book.setPrice(bookDTO.getPrice());
        book.setAuthor(bookDTO.getAuthor());
        book.setLaunchDate(bookDTO.getLaunchDate());

        book = repository.save(book);

        BookDTO result = mapper.convertToDTO(book);
        result.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).findById(result.getKey())).withSelfRel());
        return result;
    }

    public void deleteById(Long id) {

        Book book = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        repository.deleteById(id);

    }
}
