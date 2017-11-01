package ch.bfh.twitter;

import ch.bfh.entities.Tweet;
import ch.bfh.repositories.HashTagRepository;
import ch.bfh.repositories.TweetRepository;
import ch.bfh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

@Component
public class UpdateTweets {

    UserRepository userRepository;
    TweetRepository tweetRepository;
    HashTagRepository hashTagRepository;
    List<Tweet> tweets;
    Twitter twitter;
    int i;


    @Autowired
    public UpdateTweets(UserRepository userRepository, TweetRepository tweetRepository, HashTagRepository hashTagRepository) {
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.hashTagRepository = hashTagRepository;
        twitter = TwitterUtil.getTwitter();
        tweets = tweetRepository.getAll();
        i = 0;
    }

    @Scheduled(fixedRate = 1000)
    private void updateTweets(){
        System.err.println("started Scheduler");
        if(tweets != null) {
            Tweet tweet = tweets.get(i);

            Status status;
            if (i == tweets.size()-1) {
                i = 0;
            } else {
                i++;
            }
            tweets = tweetRepository.getAll();

            try {
                status = twitter.showStatus(tweet.getId());
                tweet.setContent(status.getText());
                tweet.setLikes(status.getFavoriteCount());
                tweet.setRetweets(status.getRetweetCount());
                tweetRepository.update(tweet);
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        }
    }

}
