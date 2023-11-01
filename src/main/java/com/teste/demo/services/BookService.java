package com.teste.demo.services;

import com.teste.demo.Repository.BookRepository;
import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.mapper.BookMapper;
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
        return mapper.convertToDTOList(repository.findAll());
    }
}
