package ch.bfh.controllers;

import be.ceau.chart.PieChart;
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
@RequestMapping("/api/piechart")
public class PieChartController {
    Set<Analyse<PieChart>> analysen;

    public PieChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<PieChart> pieChartAnalyse){
        analysen.add(pieChartAnalyse);
    }

    @RequestMapping(value = "/{pieanalyse}", method = RequestMethod.GET)
    ResponseEntity<PieChart> getChart(@PathVariable("pieanalyse")String analyse){
        Analyse<PieChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
