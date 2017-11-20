package ch.bfh.controllers;

import be.ceau.chart.RadarChart;
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
@RequestMapping("/api/radarechart")
public class RadarChartController {
    Set<Analyse<RadarChart>> analysen;

    public RadarChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<RadarChart> radarChartAnalyse){
        analysen.add(radarChartAnalyse);
    }

    @RequestMapping(value = "/{radaranalyse}", method = RequestMethod.GET)
    ResponseEntity<RadarChart> getChart(@PathVariable("radaranalyse")String analyse){
        Analyse<RadarChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }
}
