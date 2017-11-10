package ch.bfh.analyse.sampleanalyse;

import be.ceau.chart.Chart;
import be.ceau.chart.LineChart;
import be.ceau.chart.data.LineData;
import be.ceau.chart.dataset.LineDataset;
import ch.bfh.analyse.Analyse;
import ch.bfh.controllers.LineChartController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleAnalyse implements Analyse {
    LineChartController lineChartController;
    String name;

    @Autowired
    public SampleAnalyse(LineChartController lineChartController) {
        /**Define a name which is also used as an Endpoint in the RestController**/
        name = "sample";

        /**Register lineChart Controller**/
        this.lineChartController = lineChartController;

        /**Register the Analysis in the Controller**/
        lineChartController.registerAnalyse(this);
    }

    @Override
    public Chart getChart() {
        LineDataset dataset = new LineDataset()
                .setLabel("sample chart")
                .setData(65, 59, 80, 81, 56, 55, 40)
                .setBorderWidth(2);

        LineData data = new LineData()
                .addLabels("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")
                .addDataset(dataset);

        return new LineChart(data);
    }

    @Override
    public String getName() {
        return name;
    }
}
