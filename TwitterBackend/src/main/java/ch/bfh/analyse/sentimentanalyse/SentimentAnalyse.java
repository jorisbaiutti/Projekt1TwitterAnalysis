package ch.bfh.analyse.sentimentanalyse;

import be.ceau.chart.Chart;
import be.ceau.chart.PieChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.PieData;
import be.ceau.chart.dataset.PieDataset;
import ch.bfh.analyse.Analyse;
import ch.bfh.categorizer.OpenNLPCategorizer;
import ch.bfh.controllers.PieChartController;
import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Hashtable;
import java.util.List;

@Component
public class SentimentAnalyse implements Analyse{
    TweetRepository tweetRepository;
    PieChartController pieChartController;
    Hashtable<String, Integer> sentimentCount;
    OpenNLPCategorizer sentimentCategorizer;
    String name;
    List<Tweet> tweets;

    @Autowired
    public SentimentAnalyse(TweetRepository tweetRepository, PieChartController pieChartController) {
        this.tweetRepository = tweetRepository;
        this.pieChartController = pieChartController;

        name = "sentimentanalyse";

        sentimentCount = new Hashtable<>();
        tweets = tweetRepository.getAll();

        sentimentCount.put("Positive", 0);
        sentimentCount.put("Negative", 0);

        findTweets();
        pieChartController.registerAnalyse(this);
    }

    private void findTweets(){

        tweets.forEach(tweet -> {
            String sentiment = sentimentCategorizer.getTweetSentiment(tweet);
            sentimentCount.put(sentiment, sentimentCount.get(sentiment + 1));
        });

    }

    @Override
    public Chart getChart() {

        PieDataset dataset = new PieDataset()
                .setLabel("Sentiment Analyse")
                .addBackgroundColors(Color.GREEN, Color.RED)
                .setBorderWidth(2);
        sentimentCount.values().forEach(v -> dataset.addData(v));

        PieData data = new PieData()
                .addDataset(dataset);
        sentimentCount.keySet().forEach(k -> data.addLabel(k));

        return new PieChart(data);

    }

    @Override
    public String getName() {
        return name;
    }
}
