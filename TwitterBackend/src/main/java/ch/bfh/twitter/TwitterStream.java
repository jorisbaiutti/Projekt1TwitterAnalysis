package ch.bfh.twitter;

import ch.bfh.entities.HashTag;
import ch.bfh.entities.Tweet;
import ch.bfh.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class TwitterStream {
    @Autowired
    public void readTwitterFeed(){
        twitter4j.TwitterStream stream = TwitterStreamBuilderUtil.getStream();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
                HashtagEntity[] hashtags = status.getHashtagEntities();
                Status tweet = status;
                List<HashTag> hashTagList = new ArrayList<>();
                List<User> mentionsList = new ArrayList<>();
                System.out.println("Inhalt: ");
                System.out.println(status.getText());

                System.out.println(status.getId());


                System.out.println("RetweetCount: " + status.getRetweetCount());
                System.out.println("Hashtags: ");
                Arrays.asList(status.getHashtagEntities()).forEach(t -> System.out.println(t.getText()));

                System.out.println("Mentions: ");
                Arrays.asList(status.getUserMentionEntities()).forEach(t -> System.out.println(t.getScreenName()));

                System.out.println("Like count: " + status.getFavoriteCount());

                System.out.println("Creator: " + status.getUser().getScreenName());




            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {

            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {

            }

            @Override
            public void onStallWarning(StallWarning warning) {

            }

            @Override
            public void onException(Exception ex) {

            }
        };
        FilterQuery qry = new FilterQuery();
        String[] keywords = { "BFH","Digital Society","from:joris_baiutti"};

        qry.track(keywords);

        stream.addListener(listener);
        stream.filter(qry);
    }
}
