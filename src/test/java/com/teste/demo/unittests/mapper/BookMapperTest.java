package com.teste.demo.unittests.mapper;

import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.mapper.BookMapper;
import com.teste.demo.mapper.MyModelMapper;
import com.teste.demo.model.Book;
import jakarta.persistence.Entity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookMapperTest {

    private MockBook mockBook;
    private BookMapper mapper;

    @BeforeEach
    public void init(){

        mockBook = new MockBook();
        mapper = new BookMapper(new MyModelMapper());

    }

    @Test
    public void parseEntityToDtoTest(){
        Book entity = mockBook.mockBook(1);

        BookDTO dto = mapper.convertToDTO(entity);

        assertEquals(dto.getKey(), entity.getId());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getAuthor(), entity.getAuthor());
        assertEquals(dto.getLaunchDate(), entity.getLaunchDate());
        assertEquals(dto.getTitle(), entity.getTitle());
    }

    @Test
    public void parseDtoToEntityTest(){

        BookDTO dto = mockBook.mockDto(1);
        Book entity = mapper.convertToBook(dto);

        assertEquals(dto.getKey(), entity.getId());
        assertEquals(dto.getPrice(), entity.getPrice());
        assertEquals(dto.getAuthor(), entity.getAuthor());
        assertEquals(dto.getLaunchDate(), entity.getLaunchDate());
        assertEquals(dto.getTitle(), entity.getTitle());

    }


    @Test
    public void parseDtoToBookListTest(){

        List<BookDTO> dtos = mockBook.mockListOdDto();
        List<Book> entities = new ArrayList<>();

        for(BookDTO dto : dtos){
            entities.add(mapper.convertToBook(dto));
        }

        for (int i = 0; i < dtos.size(); i++) {
            var entity = entities.get(i);
            var dto = dtos.get(i);

            assertEquals(dto.getKey(), entity.getId());
            assertEquals(dto.getPrice(), entity.getPrice());
            assertEquals(dto.getAuthor(), entity.getAuthor());
            assertEquals(dto.getLaunchDate(), entity.getLaunchDate());
            assertEquals(dto.getTitle(), entity.getTitle());

        }
    }

    @Test
    public void parseBookToDtoListTest(){

        List<BookDTO> dtos = new ArrayList<>();
        List<Book> entities = mockBook.mockListOfBooks();

        for(Book book : entities){
            dtos.add(mapper.convertToDTO(book));
        }

        for (int i = 0; i < dtos.size(); i++) {
            var entity = entities.get(i);
            var dto = dtos.get(i);

            assertEquals(dto.getKey(), entity.getId());
            assertEquals(dto.getPrice(), entity.getPrice());
            assertEquals(dto.getAuthor(), entity.getAuthor());
            assertEquals(dto.getLaunchDate(), entity.getLaunchDate());
            assertEquals(dto.getTitle(), entity.getTitle());

        }
    }

}
