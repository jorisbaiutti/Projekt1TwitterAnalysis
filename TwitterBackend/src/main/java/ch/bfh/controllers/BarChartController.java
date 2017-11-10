package ch.bfh.controllers;

import be.ceau.chart.BarChart;
import ch.bfh.analyse.Analyse;
import ch.bfh.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Component
@RestController
@RequestMapping("/api/barchart")
public class BarChartController {
    List<Analyse<BarChart>> analysen;

    public BarChartController() {
        analysen = new ArrayList<>();
    }

    public void registerAnalyse(Analyse<BarChart> lineChartAnalyse){
        analysen.add(lineChartAnalyse);
    }

    @RequestMapping(value = "/{baranalyse}", method = RequestMethod.GET)
    ResponseEntity<BarChart> getCharts(@PathVariable("baranalyse")String analyse){
        Analyse<BarChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
