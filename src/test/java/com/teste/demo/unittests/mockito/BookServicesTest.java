package com.teste.demo.unittests.mockito;


import com.teste.demo.Repository.BookRepository;
import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.exceptions.handler.RequiredObjectInNullException;
import com.teste.demo.mapper.BookMapper;
import com.teste.demo.model.Book;
import com.teste.demo.services.BookService;
import com.teste.demo.services.PersonService;
import com.teste.demo.unittests.mapper.MockBook;
import org.aspectj.weaver.MissingResolvedTypeWithKnownSignature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class BookServicesTest {

    MockBook input;

    @InjectMocks
    private BookService service;

    @Mock
    private BookRepository repository;

    @Mock
    private BookMapper bookMapper;

    private BookMapper realbookMapper = new BookMapper(new ModelMapper());

    @BeforeEach
    void init(){
        input = new MockBook();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll(){

        var books = input.mockListOfBooks();

        Mockito.when(repository.findAll()).thenReturn(books);
        Mockito.when(bookMapper.convertToDTOList(books)).thenReturn(realbookMapper.convertToDTOList(books));

        var list = service.findAll();

        assertNotNull(list);

        for(int i = 1; i <= list.size(); i++){
            var result = list.get(i - 1);

            assertNotNull(result);
            assertNotNull(result.getKey());
            assertNotNull(result.getLinks());
            assertTrue(result.getLinks().toString().contains("</api/book/v1/" + result.getKey() + ">;rel=\"self\""));
            assertEquals(result.getTitle(), "Title " + result.getKey());
            assertEquals(result.getAuthor(), "Author " + result.getKey());
            assertEquals(result.getPrice(), 1000 * result.getKey());
            assertEquals(result.getLaunchDate(), new Date(Objects.hashCode(result.getKey())));

        }

    }

    @Test
    void testFindById(){

        Book book = input.mockBook(2);

        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(book));
        Mockito.when(bookMapper.convertToDTO(book)).thenReturn(realbookMapper.convertToDTO(book));

        BookDTO result = service.findById(2L);
        assertNotNull(result);
        assertNotNull(result.getKey());
        assertNotNull(result.getLinks());
        assertTrue(result.getLinks().toString().contains("</api/book/v1/2>;rel=\"self\""));
        assertEquals(result.getTitle(), "Title 2");
        assertEquals(result.getAuthor(), "Author 2");
        assertEquals(result.getPrice(), 1000 * 2);
        assertEquals(result.getLaunchDate(), new Date(Objects.hashCode(2L)));
    }

    @Test
    void testCreateNullBook() {
        Exception exception = assertThrows(RequiredObjectInNullException.class, () -> service.createBook(null));

        String expectedMessage = "It is not allowed to persist a null object";
        String response = exception.getMessage();

        assertEquals(expectedMessage, response);
    }

    @Test
    void testUpdateNullPerson() {
        Exception exception = assertThrows(RequiredObjectInNullException.class, () -> service.update(null));

        String expectedMessage = "It is not allowed to persist a null object";
        String response = exception.getMessage();

        assertEquals(expectedMessage, response);
    }


}
