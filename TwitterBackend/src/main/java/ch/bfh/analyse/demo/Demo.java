package ch.bfh.analyse.demo;

import be.ceau.chart.Chart;
import be.ceau.chart.PieChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.PieData;
import be.ceau.chart.dataset.PieDataset;
import ch.bfh.analyse.Analyse;
import ch.bfh.controllers.PieChartController;
import ch.bfh.repositories.TweetRepository;
import org.springframework.stereotype.Component;

import java.util.Observer;

@Component
public class Demo implements Analyse {
    String name;
    PieChartController pieChartController;
    TweetRepository tweetRepository;


    public Demo(PieChartController pieChartController, TweetRepository tweetRepository){
        this.pieChartController = pieChartController;
        this.tweetRepository = tweetRepository;


        name = "demo";
        pieChartController.registerAnalyse(this);
    }

    @Override
    public Chart getChart() {
        PieDataset dataset = new PieDataset()
                .setLabel("demo")
                .setData(20, 30, 50, 40)
                .setBorderWidth(2)
                .addBackgroundColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW);

        PieData data = new PieData()
                .addLabels("TEst 20", "Test 30", "Test 50", "Test 40")
                .addDataset(dataset);

        return new PieChart(data);
    }

    @Override
    public String getName() {
        return name;
    }


}