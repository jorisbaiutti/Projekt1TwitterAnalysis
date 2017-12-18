package ch.bfh.analyse.sentimentanalyse;

import be.ceau.chart.Chart;
import be.ceau.chart.DoughnutChart;
import be.ceau.chart.color.Color;
import be.ceau.chart.data.DoughnutData;
import be.ceau.chart.dataset.DoughnutDataset;
import ch.bfh.analyse.Analyse;
import ch.bfh.categorizer.ModelLanguage;
import ch.bfh.categorizer.SentimentCategorizer;
import ch.bfh.controllers.DoughnutChartController;
import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Hashtable;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

//@Component
public class SentimentAnalyse implements Analyse, Observer{
    private TweetRepository tweetRepository;
    private DoughnutChartController doughnutChartController;
    private Hashtable<String, Integer> sentimentAnalyse;
    private SentimentCategorizer sentimentCategorizer;
    private String name;
    private List<Tweet> tweets;
    private String goldStandardFileURL;
    private int sentimentValue = 0;
    private int maxValue = 0;
    private int sentimentValuePositiveinPercent;
    private int sentimentValueNegativeinPercent;

    @Autowired
    public SentimentAnalyse(TweetRepository tweetRepository, DoughnutChartController doughnutChartController, SentimentCategorizer sentimentCategorizer) {

        this.tweetRepository = tweetRepository;
        this.doughnutChartController = doughnutChartController;
        this.sentimentCategorizer = sentimentCategorizer;

        name = "sentimentanalyse";

        // all the URL of the golden standard file for each language
        goldStandardFileURL = "C:\\Users\\Patrick\\IdeaProjects\\Projekt1TwitterAnalysis\\TwitterBackend\\src\\main\\resources\\inputfiles\\sentimentinputEN.txt";
        sentimentCategorizer.trainModel(goldStandardFileURL, ModelLanguage.EN);

        goldStandardFileURL = "C:\\Users\\Patrick\\IdeaProjects\\Projekt1TwitterAnalysis\\TwitterBackend\\src\\main\\resources\\inputfiles\\sentimentinputDE.txt";
        sentimentCategorizer.trainModel(goldStandardFileURL, ModelLanguage.DE);

        sentimentAnalyse = new Hashtable<>();
        tweets = tweetRepository.getAll();

        setValueToSentimentAnalyse();
        doughnutChartController.registerAnalyse(this);
    }

    /**
     * for each tweeet get the language, and the sentiment in case of this language and add it to the overall sentiment
     * sentiments are between 0 and 9.
     * Result in percent positive and negative
     */

    private void setValueToSentimentAnalyse(){
        sentimentValue = 0;
        tweets.forEach(tweet -> {
            ModelLanguage language;
            try {
                language = ModelLanguage.valueOf(tweet.getLanguage());
            } catch (IllegalArgumentException E){
                language = ModelLanguage.EN;
            }
            sentimentValue += sentimentCategorizer.getSentiment(tweet.getContent(), language);
        });
        maxValue = tweetRepository.getAll().size() * 9;
        sentimentValuePositiveinPercent = 100 * sentimentValue/maxValue;
        sentimentValueNegativeinPercent = 100 - sentimentValuePositiveinPercent;

        sentimentAnalyse.put("% Positive", sentimentValuePositiveinPercent);
        sentimentAnalyse.put("% Negative", sentimentValueNegativeinPercent);
    }

    @Override
    public Chart getChart() {

        DoughnutDataset dataset = new DoughnutDataset()
                .setLabel("Sentiment Analyse in %")
                .addBackgroundColors(new Color(250,165,0), new Color(105, 125, 145))
                .setBorderWidth(2);
        sentimentAnalyse.values().forEach(v -> dataset.addData(v));

        DoughnutData data = new DoughnutData()
                .addDataset(dataset);
        sentimentAnalyse.keySet().forEach(k -> data.addLabel(k));

        return new DoughnutChart(data);

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        setValueToSentimentAnalyse();
    }

    public int getSentimentValuePositiveinPercent() {
        return sentimentValuePositiveinPercent;
    }

    public int getSentimentValueNegativeinPercent() {
        return sentimentValueNegativeinPercent;
    }

    public int getSentimentValue() {
        return sentimentValue;
    }

    public int getMaxValue() {
        return maxValue;
    }
}