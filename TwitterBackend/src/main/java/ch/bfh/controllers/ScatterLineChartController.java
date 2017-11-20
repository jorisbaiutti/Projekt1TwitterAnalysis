package ch.bfh.controllers;

import be.ceau.chart.ScatterLineChart;
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
@RequestMapping("/api/scatterlinechart")
public class ScatterLineChartController {
    Set<Analyse<ScatterLineChart>> analysen;

    public ScatterLineChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<ScatterLineChart> scatterLineChartAnalyse){
        analysen.add(scatterLineChartAnalyse);
    }

    @RequestMapping(value = "/{scatterlineanalyse}", method = RequestMethod.GET)
    ResponseEntity<ScatterLineChart> getChart(@PathVariable("scatterlineanalyse")String analyse){
        Analyse<ScatterLineChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
