package ch.bfh.analyse.tweetthemebyretweet;

import be.ceau.chart.BarChart;
import ch.bfh.analyse.languageanalyse.TweetsbyLanguage;
import ch.bfh.analyse.tweetsbytheme.TweetsbyTheme;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TweetthemebyRetweet.class)
@ComponentScan(basePackages = { "ch.bfh.analyse.tweetthemebyretweet", "ch.bfh.controllers", "ch.bfh.repositories", "ch.bfh.util" })
public class TweetthemebyRetweetTest {

    @Autowired
    TweetthemebyRetweet tweetthemebyRetweet;

    @Test
    public void getName() throws Exception {
        assert this.tweetthemebyRetweet.getName().equalsIgnoreCase("countretweetsbytheme");
    }

    @Test
    public void getChart(){
        assert (this.tweetthemebyRetweet.getChart().getClass() == BarChart.class);
    }

}