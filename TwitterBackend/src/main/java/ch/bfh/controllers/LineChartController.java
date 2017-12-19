package ch.bfh.controllers;

        import be.ceau.chart.LineChart;
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
@RequestMapping("/api/linechart")
public class LineChartController {
    Set<Analyse<LineChart>> analysen;

    public LineChartController() {
        analysen = new HashSet<>();
    }

    /**
     *
     * register a analyse to this specific ChartController in a HashSet of analyses
     */
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

    /**
     *
     * @return a List of all registrated LineChart analyses
     */
    @ApiOperation(value = "View a list of available Analysen")
    @RequestMapping(value = "/list", method= RequestMethod.GET,produces = "application/json")
    ResponseEntity<List<String>> getAnalysen(){
        List<String> availableendpoints = analysen.stream().map(a -> a.getName()).collect(Collectors.toList());
        return ResponseEntity.ok(availableendpoints);
    }
}
