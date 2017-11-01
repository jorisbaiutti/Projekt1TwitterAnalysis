package ch.bfh.twitter;

import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterStreamBuilderUtil {
    public static TwitterStream getStream(){
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ei3vkKPxNV47SJtqIDsAeZkaO")
                .setOAuthConsumerSecret("AbP4fiUQNve2RLQpmTOrXIsNrMmkIJrWA4sEMeNU1fHNkOkM1j")
                .setOAuthAccessToken("93850052-iFl5Awix5anf9OpfIR9QV5CtfEUAC3yLRUOKFL5fE")
                .setOAuthAccessTokenSecret("Vi44Q4rnw4eCvWArW79mRHqMIffvo6FObYcF5TxxZhssE");

        return new TwitterStreamFactory(cb.build()).getInstance();
    }
}
