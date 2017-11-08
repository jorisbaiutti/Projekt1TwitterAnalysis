package ch.bfh.controllers;

import ch.bfh.assembler.PersonRecourceAssembler;
import ch.bfh.beans.Person;
import ch.bfh.exception.EntityNotFoundException;
import ch.bfh.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private PersonRepository personRepository;
    private PersonRecourceAssembler personRecourceAssembler;


    @Autowired
    public PersonController(PersonRecourceAssembler personRecourceAssembler, PersonRepository personRepository) {
        this.personRepository = personRepository;
        this.personRecourceAssembler = personRecourceAssembler;
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<Resource<Person>> getPerson(@PathVariable("id")Integer id){
        Person person = personRepository.findbyId(id);
        if(person == null)throw new EntityNotFoundException("Person not found - id: " + id);
        Resource<Person> resource = personRecourceAssembler.toResource(person);
        return ResponseEntity.ok(resource);
    }
}
