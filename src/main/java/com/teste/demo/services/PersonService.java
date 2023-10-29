package com.teste.demo.services;

import com.teste.demo.Repository.PersonRepository;
import com.teste.demo.data.dto.v1.PersonDTO;
import com.teste.demo.exceptions.handler.ResourceNotFoundException;
import com.teste.demo.mapper.MyModelMapper;
import com.teste.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());


    private final PersonRepository personRepository;
    private final MyModelMapper modelMapper;

    public PersonService(PersonRepository personRepository, MyModelMapper modelMapper){
        this.personRepository = personRepository;
        this.modelMapper = modelMapper;
    }

    public PersonDTO findById(Long id) {
        Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        return modelMapper.map(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll(){
        return modelMapper.convertList(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO createPerson(PersonDTO person) {
        logger.info("Creating one person!");

        Person entity = modelMapper.map(person, Person.class);
        Person saved = personRepository.save(entity);
        return modelMapper.map(saved, PersonDTO.class);
    }

    public PersonDTO updatePerson(PersonDTO person) {
        Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Not Found!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        Person saved = personRepository.save(entity);
        return modelMapper.map(saved, PersonDTO.class);
    }

    public void delete(Long id){
        Person p = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not Found!!"));
        personRepository.delete(p);
    }
}
