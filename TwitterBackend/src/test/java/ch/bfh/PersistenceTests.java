package ch.bfh;


import ch.bfh.entities.Tweet;
import ch.bfh.repositories.TweetRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Status;

public class PersistenceTests {
    @Autowired
    TweetRepository tweetRepository;

    @Test
    public void persistenceTest(Status status){

        String liveContent = status.getText();
        Tweet savedTweet = tweetRepository.findBySqlQuery(liveContent).get(0);
        assert savedTweet.getContent().equals(liveContent);
    }
}
