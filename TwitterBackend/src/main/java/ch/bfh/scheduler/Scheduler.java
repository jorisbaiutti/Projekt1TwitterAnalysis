package ch.bfh.scheduler;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Component
public class Scheduler {
    ConfigurationBuilder cb;
    TwitterFactory twitterFactory;
    Twitter twitter;

    List<String> queryStrings = Arrays.asList("from:bfh_digital");
    List<Status> tweets;

    int counter = 0;

    public Scheduler() {
        cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ei3vkKPxNV47SJtqIDsAeZkaO")
                .setOAuthConsumerSecret("AbP4fiUQNve2RLQpmTOrXIsNrMmkIJrWA4sEMeNU1fHNkOkM1j")
                .setOAuthAccessToken("93850052-iFl5Awix5anf9OpfIR9QV5CtfEUAC3yLRUOKFL5fE")
                .setOAuthAccessTokenSecret("Vi44Q4rnw4eCvWArW79mRHqMIffvo6FObYcF5TxxZhssE");
        twitterFactory = new TwitterFactory(cb.build());
        tweets = new ArrayList<>();
        twitter = twitterFactory.getInstance();
    }

   // @Scheduled(fixedRate = 10000)
    public void getTweets() throws TwitterException {
        System.out.println("getTweets");
        if(counter == queryStrings.size()) counter = 0;
        List<Status> tweets =  getTweet(queryStrings.get(0));
        tweets.forEach(tweet -> System.out.println(tweet.getText()));
        System.out.println(tweets.size());
        counter++;
    }

    public List<Status> getTweet(String query) throws TwitterException {
        Query twitterQuery = new Query(query);
        twitterQuery.setResultType(Query.RECENT);
        QueryResult tweets = twitter.search(twitterQuery);
        return tweets.getTweets();
    }
}
