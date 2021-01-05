package com.restapi.personapi.service;

import com.restapi.personapi.dto.MessageResponseDTO;
import com.restapi.personapi.dto.PersonDTO;
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

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = Person.builder()
                                    .name(personDTO.getName())
                                    .email(personDTO.getEmail())
                                    .cpf(personDTO.getCpf())
                                    .birthDate(personDTO.getBirthDate())
                                    .build();
            Person savedPerson = personRepository.save(personToSave);
            return MessageResponseDTO
                    .builder()
                    .message("Created Person whit ID " + savedPerson.getId())
                    .build();
    }
}
