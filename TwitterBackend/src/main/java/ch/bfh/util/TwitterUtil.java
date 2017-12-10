package ch.bfh.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;
@PropertySource("classpath:application.properties")
@Component
public class TwitterUtil {

    @Value("${OAuthConsumerKey}")
    String OAuthConsumerKey;
    @Value("${OAuthConsumerSecret}")
    String OAuthConsumerSecret;
    @Value("${OAuthAccessToken}")
    String OAuthAccessToken;
    @Value("${OAuthAccessTokenSecret}")
    String OAuthAccessTokenSecret;

    public  TwitterStream getStream(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(OAuthConsumerKey)
                .setOAuthConsumerSecret(OAuthConsumerSecret)
                .setOAuthAccessToken(OAuthAccessToken)
                .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);

        return new TwitterStreamFactory(cb.build()).getInstance();
    }

    public  Twitter getTwitter(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(OAuthConsumerKey)
                .setOAuthConsumerSecret(OAuthConsumerSecret)
                .setOAuthAccessToken(OAuthAccessToken)
                .setOAuthAccessTokenSecret(OAuthAccessTokenSecret);

        return new TwitterFactory(cb.build()).getInstance();
    }
}
