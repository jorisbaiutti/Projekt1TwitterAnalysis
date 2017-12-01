package ch.bfh.analyse.tweetsbytheme;

import be.ceau.chart.BarChart;
import be.ceau.chart.Chart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.BarData;
import be.ceau.chart.dataset.BarDataset;
import ch.bfh.analyse.Analyse;
import ch.bfh.controllers.BarChartController;
import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Component
public class TweetsbyTheme implements Analyse, Observer{
    TweetRepository tweetRepository;
    BarChartController barChartController;
    Hashtable<String, Integer> topicsCount;
    String name;

    @Autowired
    public TweetsbyTheme(TweetRepository tweetRepository, BarChartController barChartController) {
        this.tweetRepository = tweetRepository;
        this.barChartController = barChartController;
        tweetRepository.addObserver(this);
        name = "countbytheme";

        topicsCount = new Hashtable<>();
        topicsCount.put("Design for Future System Fitness", 0);
        topicsCount.put("Big Data",0);
        topicsCount.put("Open Data", 0);
        topicsCount.put("Gebäude",0);
        topicsCount.put("Städte",0);
        topicsCount.put("Identität",0);
        topicsCount.put("Privatsphäre",0);
        topicsCount.put("IT-Security",0);
        topicsCount.put("Cyberforensik",0);
        topicsCount.put("Gesundheitsversorgung",0);
        topicsCount.put("E-Health",0);
        findTweets();

        barChartController.registerAnalyse(this);
    }

    private void findTweets(){
        List<Tweet> tweets = tweetRepository.getAll();
        topicsCount.forEach((k,v)-> topicsCount.put(k,0));
        tweets.forEach(tweet -> {
            for(String key : topicsCount.keySet()){
                if(tweet.getContent().contains(key)){
                    topicsCount.put(key, topicsCount.get(key) + 1);
                }
            }
        });

    }

    @Override
    public Chart getChart() {

        BarDataset dataset = new BarDataset()
                .setLabel("topicscount")
                .addBackgroundColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.BLACK)
                .setBorderWidth(2);
        topicsCount.values().forEach(v -> dataset.addData(v));

        BarData data = new BarData()
                .addDataset(dataset);
        topicsCount.keySet().forEach(k -> data.addLabel(k));

        return new BarChart(data);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        findTweets();
    }
}
