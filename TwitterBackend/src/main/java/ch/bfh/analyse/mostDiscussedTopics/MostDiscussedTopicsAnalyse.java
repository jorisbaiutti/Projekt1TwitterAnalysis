package ch.bfh.analyse.mostDiscussedTopics;

import ch.bfh.analyse.KeyValueAnalyse;
import ch.bfh.analyse.analyseentities.KeyValue;
import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@Component
public class MostDiscussedTopicsAnalyse implements KeyValueAnalyse{

    TweetRepository tweetRepository;
    Hashtable<String, Integer> topicsCount;
    List<KeyValue> resultList;

    @Autowired
    public MostDiscussedTopicsAnalyse(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
        topicsCount = new Hashtable<>();
        topicsCount.put("Design for Future System Fitness", 0);
        topicsCount.put("Big Data",0);
        topicsCount.put("Open Data", 0);
        topicsCount.put("Gebäude",0);
        topicsCount.put("Städte",0)
;       topicsCount.put("Identität",0);
        topicsCount.put("Privatsphäre",0);
        topicsCount.put("IT-Security",0);
        topicsCount.put("Cyberforensik",0);
        topicsCount.put("Gesundheitsversorgung",0);
        topicsCount.put("E-Health",0);
        resultList = new ArrayList<>();

    }

    private void findTweets(){
        List<Tweet> tweets = tweetRepository.getAll();

        tweets.forEach(tweet -> {
           for(String key : topicsCount.keySet()){
               if(tweet.getContent().contains(key)){
                   topicsCount.put(key, topicsCount.get(key) + 1);
               }
           }
        });

        topicsCount.forEach((k,v) -> resultList.add(new KeyValue(k,v)));
    }

    public List<KeyValue> getResults() {
        findTweets();
        return resultList;
    }
}