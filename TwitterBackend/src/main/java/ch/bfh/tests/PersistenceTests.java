package ch.bfh.tests;


import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.junit.Test;
import twitter4j.Status;

/**
 * Created by Patrick on 29.11.2017.
 */

public class PersistenceTests {
    TweetRepository tweetRepository;

    @Test
    public void persistenceTest(Status status){

        String liveContent = status.getText();
        Tweet savedTweet = tweetRepository.findBySqlQuery(liveContent).get(0);
        assert savedTweet.getContent().equals(liveContent);
    }
}
