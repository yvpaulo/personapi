package com.restapi.personapi.service;

import com.restapi.personapi.dto.MessageResponseDTO;
import com.restapi.personapi.entity.Person;
import com.restapi.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(Person person){
        Person savedPerson = personRepository.save(person);
        return MessageResponseDTO
                .builder()
                .message("Created Person whit ID " + savedPerson.getId())
                .build();
    }
}
