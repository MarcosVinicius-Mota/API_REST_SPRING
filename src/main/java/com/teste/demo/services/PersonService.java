package com.teste.demo.services;

import com.teste.demo.Repository.PersonRepository;
import com.teste.demo.controller.PersonController;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.exceptions.handler.RequiredObjectInNullException;
import com.teste.demo.exceptions.handler.ResourceNotFoundException;
import com.teste.demo.mapper.MyModelMapper;
import com.teste.demo.mapper.PersonMapper;
import com.teste.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    @SuppressWarnings("FieldMayBeFinal")
    private PersonRepository personRepository;

    @SuppressWarnings("FieldMayBeFinal")
    private PersonMapper personMapper;

    @Autowired
    public PersonService(PersonRepository personRepository, PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    public PersonDTO findById(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        PersonDTO dto = personMapper.personToDto(entity);

        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(id)).withSelfRel());
        return dto;
    }

    public List<PersonDTO> findAll(){
        var persons =  personMapper.personToDtoList(personRepository.findAll());

        persons.forEach(x -> {
            x.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(x.getKey())).withSelfRel());
        });

        return persons;
    }

    public PersonDTO createPerson(PersonDTO person) {

        if(person == null){
            throw new RequiredObjectInNullException();
        }

        logger.info("Creating one person!");
        Person entity = personMapper.dtoToPerson(person);
        Person saved = personRepository.save(entity);
        PersonDTO dto = personMapper.personToDto(saved);
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public PersonDTO updatePerson(PersonDTO person) {
        if(person == null){
            throw new RequiredObjectInNullException();
        }

        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Not Found!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        Person saved = personRepository.save(entity);
        PersonDTO dto = personMapper.personToDto(saved);
        dto.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController.class).findById(dto.getKey())).withSelfRel());
        return dto;
    }

    public void delete(Long id){
        Person p = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found!!"));
        personRepository.delete(p);
    }
}
