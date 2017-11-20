package ch.bfh.controllers;

import be.ceau.chart.BubbleChart;
import ch.bfh.analyse.Analyse;
import ch.bfh.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@Component
@RestController
@RequestMapping("/api/bubblechart")
public class BubbleChartController {
    Set<Analyse<BubbleChart>> analysen;

    public BubbleChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<BubbleChart> BubbleChartAnalyse){
        analysen.add(BubbleChartAnalyse);
    }

    @RequestMapping(value = "/{bubbleanalyse}", method = RequestMethod.GET)
    ResponseEntity<BubbleChart> getChart(@PathVariable("bubbleanalyse")String analyse){
        Analyse<BubbleChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
