package com.teste.demo.mapper;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookMapper {

    @SuppressWarnings("FieldMayBeFinal")
    private MyModelMapper modelMapper;

    public BookMapper(MyModelMapper modelMapper){
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
        return modelMapper.convertList(list, BookDTO.class);
    }

    public List<Book> convertToBookList(List<BookDTO> list){
        return modelMapper.convertList(list, Book.class);
    }

}
