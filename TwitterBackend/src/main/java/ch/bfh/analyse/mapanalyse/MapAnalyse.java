package ch.bfh.analyse.mapanalyse;

import be.ceau.chart.Chart;
import ch.bfh.analyse.Analyse;
import ch.bfh.chartsmodel.GoogleMap;
import ch.bfh.chartsmodel.GoogleMarker;
import ch.bfh.controllers.MapChartController;
import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MapAnalyse implements Analyse{
    MapChartController mapChartController;
    TweetRepository tweetRepository;
    String name;

    @Autowired
    public MapAnalyse(MapChartController mapChartController, TweetRepository tweetRepository) {
        this.mapChartController = mapChartController;
        this.tweetRepository = tweetRepository;
        name = "mapanalyse";
        mapChartController.registerAnalyse(this);
    }

    /**
     *
     * @return the chart which can be used for display the analyse
     */
    @Override
    public Chart getChart() {
        GoogleMap googleMap = new GoogleMap("testmap",8.5661791,46.8207642);
        List<Tweet> tweets = tweetRepository.getAll();
        tweets.forEach(t -> {
            if(t.getLongitude() != 0){
                googleMap.addMarker(new GoogleMarker(t.getCreator().getUserName(),t.getLatitude(),t.getLongitude()));
            }
        });
        return googleMap;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
