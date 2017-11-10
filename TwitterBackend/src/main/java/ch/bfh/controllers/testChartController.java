package ch.bfh.controllers;

import be.ceau.chart.BarChart;
import ch.bfh.analyse.analyseentities.KeyValue;
import ch.bfh.analyse.mostDiscussedTopics.MostDiscussedTopicsAnalyse;
import ch.bfh.assembler.KeyValueResourceAssembler;
import ch.bfh.assembler.testChartAssembler;
import ch.bfh.beans.Person;
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
@RequestMapping("/api/charts")
public class testChartController {
    MostDiscussedTopicsAnalyse mostDiscussedTopicsAnalyse;
    testChartAssembler testChartAssembler;
    List<String> analyseList = Arrays.asList("MostDiscussedTopics");

    @Autowired
    public testChartController(MostDiscussedTopicsAnalyse mostDiscussedTopicsAnalyse, testChartAssembler testChartAssembler) {
        this.mostDiscussedTopicsAnalyse = mostDiscussedTopicsAnalyse;
        this.testChartAssembler = testChartAssembler;
    }
    @RequestMapping(value = "/{chartname}", method = RequestMethod.GET)
    ResponseEntity<Resource<BarChart>> getValues(@PathVariable("analyse")String analyse){
        BarChart barChart = mostDiscussedTopicsAnalyse.getChart();
        if(barChart == null)throw new EntityNotFoundException("Person not found - id: ");
        Resource<BarChart> resource = testChartAssembler.toResource(barChart);
        return ResponseEntity.ok(resource);
    }
}
