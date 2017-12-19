package ch.bfh.analyse.mapanalyse;

import be.ceau.chart.Chart;
import ch.bfh.analyse.Analyse;
import ch.bfh.chartsmodel.GoogleMap;
import ch.bfh.chartsmodel.GoogleMarker;
import ch.bfh.controllers.MapChartController;
import ch.bfh.util.MapsUtil;
import ch.bfh.util.TwitterUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class Followers implements Analyse {
    MapChartController mapChartController;
    TwitterUtil twitterUtil;
    MapsUtil mapsUtil;
    Twitter twitter;
    PagableResponseList<User> followersList;
    List<User> userList;
    GeoApiContext context;
    String name;
    GoogleMap googleMap;

    public Followers(MapChartController mapChartController, TwitterUtil twitterUtil, MapsUtil mapsUtil) {
        this.mapChartController = mapChartController;
        this.twitterUtil = twitterUtil;
        twitter = twitterUtil.getTwitter();
        context = mapsUtil.getApiContext();
        name = "myfollowers";

        long cursor =-1L;

        try {
            userList = new ArrayList<>();
            IDs iDs = twitter.getFollowersIDs("@bfh_digital", cursor);
            for(long id : iDs.getIDs()){
                userList.add(twitter.showUser(id));
            }

        } catch (TwitterException e) {
            System.out.println("No valid GeoData");
        }

        googleMap = new GoogleMap("testmap",8.5661791,46.8207642);
        userList.forEach(u -> {
            GoogleMarker marker = getMarker(u.getLocation(),u.getScreenName());
            if(marker.getLat() != 0) {
                googleMap.addMarker(marker);
            }
        });
        mapChartController.registerAnalyse(this);
    }

    /**
     *
     * @return the chart which can be used for display the analyse
     */
    @Override
    public Chart getChart() {
        return googleMap;
    }

    @Override
    public String getName() {
        return name;
    }

    GoogleMarker getMarker(String address, String name){

        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context,address).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
        } catch (ApiException e) {
            System.out.println("no valid GeoData");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
       try {
           return new GoogleMarker(name, Double.parseDouble(gson.toJson(results[0].geometry.location.lat)), Double.parseDouble(gson.toJson(results[0].geometry.location.lng)));
       }catch (ArrayIndexOutOfBoundsException e){
            return new GoogleMarker("not found", 0,0);
        }
    }
}
