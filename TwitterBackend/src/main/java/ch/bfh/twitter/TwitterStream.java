
package ch.bfh.twitter;

import ch.bfh.entities.HashTag;
import ch.bfh.entities.Tweet;
import ch.bfh.entities.User;
import ch.bfh.repositories.HashTagRepository;
import ch.bfh.repositories.TweetRepository;
import ch.bfh.repositories.UserRepository;
import ch.bfh.util.TwitterUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TwitterStream {
    HashTagRepository hashTagRepository;
    UserRepository userRepository;
    TweetRepository tweetRepository;
    TwitterUtil twitterUtil;

    @Value("${twitter.twitterqueryStrings}")
    String[] keywords;
    @Value("${twitter.enabletwitterstream}")
    boolean enabletwitterstream;

    @Autowired
    public TwitterStream(HashTagRepository hashTagRepository, UserRepository userRepository, TweetRepository tweetRepository, TwitterUtil twitterUtil) {
        this.hashTagRepository = hashTagRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
        this.twitterUtil = twitterUtil;
    }

    /**
     * Has a listener which receives Tweets based on the Query Strings
     */
    @Autowired
    public void readTwitterFeed() {
        twitter4j.TwitterStream stream = twitterUtil.getStream();
        if (enabletwitterstream) {
            StatusListener listener = new StatusListener() {
                @Override
                public void onStatus(Status status) {
                    UserMentionEntity[] userMentionEntities = status.getUserMentionEntities();
                    HashtagEntity[] hashtags = status.getHashtagEntities();
                    Status tweet = status;
                    List<HashTag> hashTagList = new ArrayList<>();
                    List<User> mentionsList = new ArrayList<>();

                    User user = userRepository.findbyId(status.getUser().getId());
                    Tweet tweetentity = new Tweet();
                    if (user == null) {
                        user = new User();
                        user.setId(status.getUser().getId());
                        user.setUserName(status.getUser().getScreenName());
                        user.setLocation(status.getUser().getLocation());
                        userRepository.save(user);

                    }
                    tweetentity.setCreator(user);
                    tweetentity.setLanguage(status.getLang());
                    tweetentity.setContent(status.getText());
                    tweetentity.setId(status.getId());

                    if (status.getPlace() != null) {
                        tweetentity.setLongitude(status.getPlace().getBoundingBoxCoordinates()[0][0].getLongitude());
                    }
                    if (status.getPlace() != null) {
                        tweetentity.setLatitude(status.getPlace().getBoundingBoxCoordinates()[0][0].getLatitude());
                    }

                    List<HashTag> hashTags = Arrays.asList(status.getHashtagEntities()).stream().map(h -> new HashTag(h.getText())).collect(Collectors.toList());
                    tweetentity.setHashTags(hashTags);
                    tweetRepository.save(tweetentity);
                    System.out.println(tweetentity.getContent());

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
            qry.track(keywords);

            stream.addListener(listener);
            stream.filter(qry);
        }
    }
}
