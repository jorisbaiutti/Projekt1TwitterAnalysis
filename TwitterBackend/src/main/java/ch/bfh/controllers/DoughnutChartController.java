package ch.bfh.controllers;

import be.ceau.chart.DoughnutChart;
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
@RequestMapping("/api/doughnutchart")
public class DoughnutChartController {
    Set<Analyse<DoughnutChart>> analysen;

    public DoughnutChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<DoughnutChart> doughnutChartAnalyse){
        analysen.add(doughnutChartAnalyse);
    }

    @RequestMapping(value = "/{doughnutanalyse}", method = RequestMethod.GET)
    ResponseEntity<DoughnutChart> getChart(@PathVariable("doughnutanalyse")String analyse){
        Analyse<DoughnutChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
