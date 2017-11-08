
package ch.bfh.controllers;

import ch.bfh.analyse.analyseentities.KeyValue;
import ch.bfh.analyse.mostDiscussedTopics.MostDiscussedTopicsAnalyse;
import ch.bfh.assembler.KeyValueResourceAssembler;
import ch.bfh.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/keyvalueanalyse")
public class KeyValueController {
    MostDiscussedTopicsAnalyse mostDiscussedTopicsAnalyse;
    KeyValueResourceAssembler keyValueResourceAssembler;
    List<String> analyseList = Arrays.asList("MostDiscussedTopics");

    @Autowired
    public KeyValueController(MostDiscussedTopicsAnalyse mostDiscussedTopicsAnalyse, KeyValueResourceAssembler keyValueResourceAssembler) {
        this.mostDiscussedTopicsAnalyse = mostDiscussedTopicsAnalyse;
        this.keyValueResourceAssembler = keyValueResourceAssembler;
    }
    @RequestMapping(value = "/{analyse}", method = RequestMethod.GET)
    ResponseEntity<List<Resource<KeyValue>>> getValues(@PathVariable("analyse")String analyse){
        List<KeyValue> keyValues = mostDiscussedTopicsAnalyse.getResults();
        List<Resource<KeyValue>> keyValueressources = new ArrayList<>();
        keyValues.forEach(kv -> {
            keyValueressources.add(keyValueResourceAssembler.toResource(kv));
        });
        if(keyValues == null)throw new EntityNotFoundException("Resource not found - analyse: " + analyse);

        return ResponseEntity.ok(keyValueressources);
    }
}
