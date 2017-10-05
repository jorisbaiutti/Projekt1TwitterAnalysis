package ch.bfh;

import ch.bfh.analyse.sentiment.SentimentAnalysisWithCount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
@EnableZuulProxy
@SpringBootApplication
public class StartApplication
{

    private static int positive = 0;
    private static int negative = 0;
    public static void main( String[] args ) throws IOException, TwitterException {
        SpringApplication.run(StartApplication.class, args);

        //Test Twitter
        /*
        String line = "";
        SentimentAnalysisWithCount twitterCategorizer = new SentimentAnalysisWithCount();
        twitterCategorizer.trainModel();

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("ei3vkKPxNV47SJtqIDsAeZkaO")
                .setOAuthConsumerSecret("AbP4fiUQNve2RLQpmTOrXIsNrMmkIJrWA4sEMeNU1fHNkOkM1j")
                .setOAuthAccessToken("93850052-iFl5Awix5anf9OpfIR9QV5CtfEUAC3yLRUOKFL5fE")
                .setOAuthAccessTokenSecret("Vi44Q4rnw4eCvWArW79mRHqMIffvo6FObYcF5TxxZhssE");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query("source:BFH");
        QueryResult result = twitter.search(query);
        int result1 = 0;
        for (Status status : result.getTweets()) {
            result1 = twitterCategorizer.classifyNewTweet(status.getText());
            if (result1 == 1) {
                positive++;
            } else {
                negative++;
            }
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Projekt1TwitterAnalysis\\TwitterBackend\\src\\main\\resources\\outputFiles\\results.csv"));
        bw.write("Positive Tweets," + positive);
        bw.newLine();
        bw.write("Negative Tweets," + negative);
        bw.close();
        */
    }

}
