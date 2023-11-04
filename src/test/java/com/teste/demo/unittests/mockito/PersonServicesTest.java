package com.teste.demo.unittests.mockito;

import com.teste.demo.Repository.PersonRepository;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.exceptions.handler.RequiredObjectInNullException;
import com.teste.demo.mapper.PersonMapper;
import com.teste.demo.model.Person;
import com.teste.demo.services.PersonService;
import com.teste.demo.unittests.mapper.MockPerson;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    private PersonService service;

    @Mock
    private PersonRepository repository;

    @Mock
    private PersonMapper personMapper;

    private PersonMapper realpersonMapper = new PersonMapper(new ModelMapper());

    @BeforeEach
    void setUp() throws Exception {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {

        var persons = input.mockEntityList();
        for (int i = 0; i < persons.size(); i++) {
            persons.get(i).setId(i + 1L);
        }

        Mockito.when(repository.findAll()).thenReturn(persons);
        Mockito.when(personMapper.personToDtoList(persons)).thenReturn(realpersonMapper.personToDtoList(persons));

        var list = service.findAll();

        assertNotNull(list);

        for (int i = 0; i < list.size(); i++) {
            PersonDTO result = list.get(i);
            Assertions.assertNotNull(result);
            Assertions.assertNotNull(result.getKey());
            Assertions.assertNotNull(result.getLinks());
            System.out.println(result.toString());
            Assertions.assertTrue(result.toString().contains("links: [</api/person/v1/" + result.getKey() + ">;rel=\"self\"]"));
            assertEquals("Address Test" + i, result.getAddress());
            assertEquals("First Name Test" + i, result.getFirstName());
            assertEquals("Last Name Test" + i, result.getLastName());
            assertEquals((i & 1) != 0 ? "Female" : "Male", result.getGender());
        }

    }

    @Test
    void testFindById() {
        Person person = input.mockEntity();
        person.setId(2L);


        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(person));
        Mockito.when(personMapper.personToDto(person)).thenReturn(realpersonMapper.personToDto(person));
        var result = service.findById(2L);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());
        Assertions.assertTrue(result.toString().contains("links: [</api/person/v1/2>;rel=\"self\"]"));
        assertEquals("Address Test0", result.getAddress());
        assertEquals("First Name Test0", result.getFirstName());
        assertEquals("Last Name Test0", result.getLastName());
        assertEquals("Male", result.getGender());
    }

    @Test
    void testCreate() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(personMapper.personToDto(entity)).thenReturn(dto);
        Mockito.when(personMapper.dtoToPerson(dto)).thenReturn(entity);

        PersonDTO result = service.createPerson(dto);

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());
        Assertions.assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());

    }

    @Test
    void testUpdate() {

        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));
        Mockito.when(repository.save(entity)).thenReturn(entity);
        Mockito.when(personMapper.personToDto(entity)).thenReturn(dto);
        //Mockito.when(personMapper.dtoToPerson(dto)).thenReturn(entity);

        PersonDTO result = service.updatePerson(dto);


        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.getKey());
        Assertions.assertNotNull(result.getLinks());
        Assertions.assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
        assertEquals("Address Test1", result.getAddress());
        assertEquals("First Name Test1", result.getFirstName());
        assertEquals("Last Name Test1", result.getLastName());
        assertEquals("Female", result.getGender());
    }

    @Test
    void testDelete() {
        Person entity = input.mockEntity(1);
        entity.setId(1L);

        PersonDTO dto = input.mockDTO(1);

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(entity));


        service.delete(1L);

    }

    @Test
    void testCreateNullPerson() {
        Exception exception = assertThrows(RequiredObjectInNullException.class, () -> service.createPerson(null));

        String expectedMessage = "It is not allowed to persist a null object";
        String response = exception.getMessage();

        assertEquals(expectedMessage, response);
    }

    @Test
    void testUpdateNullPerson() {
        Exception exception = assertThrows(RequiredObjectInNullException.class, () -> service.updatePerson(null));

        String expectedMessage = "It is not allowed to persist a null object";
        String response = exception.getMessage();

        assertEquals(expectedMessage, response);
    }

}
