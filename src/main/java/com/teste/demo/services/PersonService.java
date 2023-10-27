package com.teste.demo.services;

import com.teste.demo.Repository.PersonRepository;
import com.teste.demo.controller.PersonController;
import com.teste.demo.exceptions.handler.ResourceNotFoundException;
import com.teste.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Service
public class PersonService {

    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public Person findById(Long id){
        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public List<Person> findAll(){
        logger.info("Finding all people!");
        return personRepository.findAll();
    }


    public Person createPerson(Person person) {

        logger.info("Creating one person!");
        return personRepository.save(person);
    }

    public Person updatePerson(Person person) {

        logger.info("Updating person!");
        Person p = findById(person.getId());

        p.setFirstName(person.getFirstName());
        p.setLastName(person.getLastName());
        p.setGender(person.getGender());
        p.setAddress(person.getAddress());

        return personRepository.save(p);
    }

    public void delete(Long id){
        logger.info("Deleting id: " + id);
        Person p = findById(id);
        personRepository.delete(p);
    }

}
