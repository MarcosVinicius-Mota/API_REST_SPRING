package com.teste.demo.mapper;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.model.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookMapper {

    @SuppressWarnings("FieldMayBeFinal")
    private ModelMapper modelMapper;

    public BookMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.modelMapper.typeMap(BookDTO.class, Book.class).addMapping(BookDTO::getKey, Book::setId);
        this.modelMapper.typeMap(Book.class, BookDTO.class).addMapping(Book::getId, BookDTO::setKey);
    }

    public BookDTO convertToDTO(Book book){
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertToBook(BookDTO dto){
        return modelMapper.map(dto, Book.class);
    }

    public List<BookDTO> convertToDTOList(List<Book> list){
        List<BookDTO> DtoList = new ArrayList<>();
        for(Book book : list){
            DtoList.add(convertToDTO(book));
        }
        return DtoList;
    }

    public List<Book> convertToBookList(List<BookDTO> list){
        List<Book> books = new ArrayList<>();
        for(BookDTO dto : list){
            books.add(convertToBook(dto));
        }
        return books;
    }

}
