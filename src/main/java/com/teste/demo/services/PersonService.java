package com.teste.demo.services;

import com.teste.demo.Repository.PersonRepository;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.exceptions.handler.ResourceNotFoundException;
import com.teste.demo.mapper.MyModelMapper;
import com.teste.demo.mapper.PersonMapper;
import com.teste.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonService(PersonRepository personRepository, PersonMapper personMapper){
        this.personRepository = personRepository;
        this.personMapper = personMapper;

    }

    public PersonDTO findById(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return personMapper.personToDto(entity);
    }

    public List<PersonDTO> findAll(){
        return personMapper.personToDtoList(personRepository.findAll());
    }

    public PersonDTO createPerson(PersonDTO person) {
        logger.info("Creating one person!");

        Person entity = personMapper.dtoToPerson(person);
        Person saved = personRepository.save(entity);
        return personMapper.personToDto(saved);
    }

    public PersonDTO updatePerson(PersonDTO person) {
        Person entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Not Found!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        Person saved = personRepository.save(entity);
        return personMapper.personToDto(saved);
    }

    public void delete(Long id){
        Person p = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found!!"));
        personRepository.delete(p);
    }
}
