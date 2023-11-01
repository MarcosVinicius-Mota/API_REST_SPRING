package com.teste.demo.mapper;


import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonMapper {

    @SuppressWarnings("FieldMayBeFinal")
    private MyModelMapper modelMapper;
    public PersonMapper(MyModelMapper modelMapper){
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
        return modelMapper.convertList(personList, PersonDTO.class);
    }

    public List<Person> dtoToPersonList(List<PersonDTO> dtoList){
        return modelMapper.convertList(dtoList, Person.class);
    }

}
