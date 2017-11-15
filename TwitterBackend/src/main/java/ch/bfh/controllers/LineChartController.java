package ch.bfh.controllers;

import be.ceau.chart.LineChart;
import ch.bfh.analyse.Analyse;
import ch.bfh.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RestController
@RequestMapping("/api/linecharts")
public class LineChartController {
    Set<Analyse<LineChart>> analysen;

    public LineChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<LineChart> lineChartAnalyse){
        analysen.add(lineChartAnalyse);
    }

    @RequestMapping(value = "/{lineanalyse}", method = RequestMethod.GET)
    ResponseEntity<LineChart> getChart(@PathVariable("lineanalyse")String analyse){
        Analyse<LineChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
