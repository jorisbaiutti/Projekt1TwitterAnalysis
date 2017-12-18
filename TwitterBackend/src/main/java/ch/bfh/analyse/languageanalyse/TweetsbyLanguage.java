package ch.bfh.analyse.languageanalyse;

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

//@Component
public class TweetsbyLanguage implements Analyse{
    TweetRepository tweetRepository;
    BarChartController barChartController;
    Hashtable<String, Integer> languageCount;
    String name;
    List<Tweet> tweets;

    @Autowired
    public TweetsbyLanguage(TweetRepository tweetRepository, BarChartController barChartController) {
        this.tweetRepository = tweetRepository;
        this.barChartController = barChartController;

        name = "countbylanguage";

        languageCount = new Hashtable<>();
        tweets = tweetRepository.getAll();

        setChartParameters();
        setCountOfLanguagebyTweet();
        barChartController.registerAnalyse(this);
    }

    /**
     * sets all the different languages of the tweets to the analyse
     */
    private void setChartParameters() {
        tweets.forEach(tweet -> {
            languageCount.put(tweet.getLanguage(), 0);
        });
    }

    /**
     * sets the value count of the different languages
     */
    private void setCountOfLanguagebyTweet(){
        tweets.forEach(tweet -> {
            for(String key : languageCount.keySet()){
                if(tweet.getLanguage().contains(key)){
                    languageCount.put(key, languageCount.get(key) + 1);
                }
            }
        });
    }

    /**
     *
     * @return the chart which can be used for display the analyse
     */

    @Override
    public Chart getChart() {

        BarDataset dataset = new BarDataset()
                .setLabel("languageCount")
                .addBackgroundColors(Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.ORANGE, Color.GRAY, Color.BLACK)
                .setBorderWidth(2);
        languageCount.values().forEach(v -> dataset.addData(v));

        BarData data = new BarData()
                .addDataset(dataset);
        languageCount.keySet().forEach(k -> data.addLabel(k));

        return new BarChart(data);

    }

    @Override
    public String getName() {
        return name;
    }
}
