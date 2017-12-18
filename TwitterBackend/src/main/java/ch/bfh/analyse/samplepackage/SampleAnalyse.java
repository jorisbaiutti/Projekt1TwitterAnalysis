package ch.bfh.analyse.samplepackage;

import be.ceau.chart.Chart;
import ch.bfh.analyse.Analyse;
import ch.bfh.controllers.BarChartController;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

public class SampleAnalyse implements Analyse {


    BarChartController barChartController;
    TweetRepository tweetRepository;

    String name;

    @Autowired
    public SampleAnalyse (BarChartController barChartController, TweetRepository tweetRepository) {
        this.barChartController = barChartController;
        this.tweetRepository = tweetRepository;

        HashMap<>
    }

    @Override
    public Chart getChart() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }
}
