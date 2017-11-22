
package ch.bfh.twitter;

import ch.bfh.entities.HashTag;
import ch.bfh.entities.Tweet;
import ch.bfh.entities.User;
import ch.bfh.repositories.HashTagRepository;
import ch.bfh.repositories.TweetRepository;
import ch.bfh.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public TwitterStream(HashTagRepository hashTagRepository, UserRepository userRepository, TweetRepository tweetRepository) {
        this.hashTagRepository = hashTagRepository;
        this.userRepository = userRepository;
        this.tweetRepository = tweetRepository;
    }

    @Autowired
    public void readTwitterFeed(){
        twitter4j.TwitterStream stream = TwitterUtil.getStream();
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

                User user = (User) userRepository.getOne(status.getUser().getId());
                Tweet tweetentity = new Tweet();
                if(user == null) {
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


                if (status.getPlace() != null){
                    tweetentity.setLongitude(status.getPlace().getBoundingBoxCoordinates()[0][0].getLongitude());
                }
                if (status.getPlace() != null){
                    tweetentity.setLatitude(status.getPlace().getBoundingBoxCoordinates()[0][0].getLatitude());
                }


                List<HashTag> hashTags = Arrays.asList(status.getHashtagEntities()).stream().map(h -> new HashTag(h.getText())).collect(Collectors.toList());


                tweetentity.setHashTags(hashTags);

                tweetRepository.save(tweetentity);

                System.err.println(tweetentity.getContent());

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
        String[] keywords = { "BFH","Digital Society","System Design","Future System","Big Data","Open Data","Gebäude und Städte","Identität","Privatsphäre","IT-Security","Cyberforensik","Gesundheitsversorgung","E-Health"};

        qry.track(keywords);

        stream.addListener(listener);
        stream.filter(qry);
    }
}
