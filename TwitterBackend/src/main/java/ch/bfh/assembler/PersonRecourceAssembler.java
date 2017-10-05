package ch.bfh.assembler;

import ch.bfh.beans.Person;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

@Component
public class PersonRecourceAssembler implements ResourceAssembler<Person,Resource<Person>> {
    @Override
    public Resource<Person> toResource(Person person) {
        Resource<Person> resource = new Resource<>(person);
        resource.add(new Link("http://persons/" + person.getId()).withSelfRel());
        return resource;
    }
}
