package com.teste.demo.unittests.mapper;

import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MockBook {

    public Book mockBook(){
        return mockBook(0);
    }

    public Book mockBook(Integer i) {
        Book mock = new Book();

        mock.setId(i.longValue());
        mock.setAuthor("Author " + i);
        mock.setLaunchDate(new Date(mock.getId().hashCode()));
        mock.setPrice(1000.0 * i);
        mock.setTitle("Title " + i);

        return mock;
    }

    public BookDTO mockDto(Integer i){
        BookDTO mock = new BookDTO();

        mock.setKey(i.longValue());
        mock.setAuthor("Author " + i);
        mock.setLaunchDate(new Date(mock.getKey().hashCode()));
        mock.setPrice(1000.0 * i);
        mock.setTitle("Title " + i);

        return mock;
    }

    public List<Book> mockListOfBooks(){
        List<Book> list = new ArrayList<>();

        for(int i = 1; i <= 15; i++){
            list.add(mockBook(i));
        }

        return list;
    }

    public List<BookDTO> mockListOdDto(){

        List<BookDTO> list = new ArrayList<>();

        for(int i = 1; i <= 15; i++){
            list.add(mockDto(i));
        }

        return list;

    }


}
