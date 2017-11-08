package ch.bfh.assembler;

import ch.bfh.analyse.analyseentities.KeyValue;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KeyValueResourceAssembler implements ResourceAssembler<KeyValue,Resource<KeyValue>> {
    @Override
    public Resource<KeyValue> toResource(KeyValue keyValue) {
        Resource<KeyValue> resource = new Resource<>(keyValue);
        resource.add(new Link("http://KeyValue/"));
        return resource;
    }
}
