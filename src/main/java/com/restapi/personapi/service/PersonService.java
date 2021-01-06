package com.restapi.personapi.service;

import com.restapi.personapi.dto.MessageResponseDTO;
import com.restapi.personapi.dto.PersonDTO;
import com.restapi.personapi.entity.Person;
import com.restapi.personapi.exception.PersonNotFoundException;
import com.restapi.personapi.mapper.PersonMapper;
import com.restapi.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private final PersonMapper personMapper = PersonMapper.INSTANCE;

    @Autowired
    public PersonService(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
            Person personToSave = personMapper.toModel(personDTO);
            Person savedPerson = personRepository.save(personToSave);
            return MessageResponseDTO
                    .builder()
                    .message("Created Person whit ID " + savedPerson.getId())
                    .build();
    }

    public List<PersonDTO> listAll() {
        List<Person> allPeople = personRepository.findAll();
        return allPeople.stream()
                .map(personMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findById(Long id) throws PersonNotFoundException {
        Person person = verifyIfExists(id);
            return personMapper.toDTO(person);

    }

    public void deleteById(Long id) throws PersonNotFoundException {
        verifyIfExists(id);
        personRepository.deleteById(id);
    }

    private Person verifyIfExists(Long id) throws PersonNotFoundException{
        return personRepository.findById(id)
                .orElseThrow(() ->new PersonNotFoundException(id));
    }
}
