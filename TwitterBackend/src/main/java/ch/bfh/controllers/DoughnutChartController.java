package ch.bfh.controllers;

import be.ceau.chart.DoughnutChart;
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

@Component
@RestController
@RequestMapping("/api/doughnutchart")
public class DoughnutChartController extends ChartController{
    Set<Analyse<DoughnutChart>> analysen;

    public DoughnutChartController() {
        analysen = new HashSet<>();
    }

    public void registerAnalyse(Analyse<DoughnutChart> doughnutChartAnalyse){
        analysen.add(doughnutChartAnalyse);
    }

    @RequestMapping(value = "/{doughnutanalyse}", method = RequestMethod.GET)
    private ResponseEntity<DoughnutChart> getChart(@PathVariable("doughnutanalyse")String analyse){
        Analyse<DoughnutChart> finalAnalyse = analysen.stream().filter(a -> a.getName().equals(analyse)).findFirst().get();
        if(finalAnalyse == null){
            throw new EntityNotFoundException("Analyse not found " + analyse);
        }
        return ResponseEntity.ok(finalAnalyse.getChart());
    }

    @ApiOperation(value = "View a list of available Analysen")
    @RequestMapping(value = "/list", method= RequestMethod.GET,produces = "application/json")
    private ResponseEntity<List<String>> getAnalysen(){
        List<String> availableendpoints = analysen.stream().map(a -> a.getName()).collect(Collectors.toList());
        return ResponseEntity.ok(availableendpoints);
    }
}
