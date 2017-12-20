package ch.bfh.controllers;

import be.ceau.chart.BarChart;
import ch.bfh.analyse.Analyse;
import ch.bfh.exception.EntityNotFoundException;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Controller for BarCharts
 */
@Component
@RestController
@RequestMapping("/api/barchart")
public class BarChartController {
    Set<Analyse<BarChart>> analysen;

    public BarChartController() {
        analysen = new HashSet<>();
    }

    /**
     *
     * register a analyse to this specific ChartController in a HashSet of analyses
     */
    public void registerAnalyse(Analyse<BarChart> barChartAnalyse){
        analysen.add(barChartAnalyse);
    }

    @RequestMapping(value = "/{baranalyse}", method = RequestMethod.GET)
    ResponseEntity<BarChart> getChart(@PathVariable("baranalyse")String analyse){
        Analyse<BarChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }

    /**
     *
     * @return a List of all registrated BarChart analyses
     */
    @ApiOperation(value = "View a list of available Analysen")
    @RequestMapping(value = "/list", method= RequestMethod.GET,produces = "application/json")
    ResponseEntity<List<String>> getAnalysen(){
        List<String> availableendpoints = analysen.stream().map(a -> a.getName()).collect(Collectors.toList());
        return ResponseEntity.ok(availableendpoints);
    }
}
