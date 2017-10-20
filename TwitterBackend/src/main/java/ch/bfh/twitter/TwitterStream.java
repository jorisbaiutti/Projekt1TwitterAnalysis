package ch.bfh.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.*;
@Component
public class TwitterStream {
    @Autowired
    public void readTwitterFeed(){
        twitter4j.TwitterStream stream = TwitterStreamBuilderUtil.getStream();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {


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
        String[] keywords = { "BFH","Digital Society"};

        qry.track(keywords);

        stream.addListener(listener);
        stream.filter(qry);
    }
}
