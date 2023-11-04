package com.teste.demo.mapper;


import com.teste.demo.data.dto.v1.BookDTO;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.model.Book;
import com.teste.demo.model.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonMapper {

    @SuppressWarnings("FieldMayBeFinal")
    private ModelMapper modelMapper;
    public PersonMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        modelMapper.typeMap(PersonDTO.class, Person.class).addMapping(PersonDTO::getKey, Person::setId);
        modelMapper.typeMap(Person.class, PersonDTO.class).addMapping(Person::getId, PersonDTO::setKey);
    }

    public PersonDTO personToDto(Person person){
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person dtoToPerson(PersonDTO dto){
        return modelMapper.map(dto, Person.class);
    }

    public List<PersonDTO> personToDtoList(List<Person> personList){
        List<PersonDTO> DtoList = new ArrayList<>();
        for(Person person : personList){
            DtoList.add(personToDto(person));
        }
        return DtoList;
    }

    public List<Person> dtoToPersonList(List<PersonDTO> dtoList){
        List<Person> persons = new ArrayList<>();
        for(PersonDTO dto : dtoList){
            persons.add(dtoToPerson(dto));
        }
        return persons;
    }

}
