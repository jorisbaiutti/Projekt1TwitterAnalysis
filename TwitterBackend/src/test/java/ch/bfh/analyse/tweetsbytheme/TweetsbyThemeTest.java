package ch.bfh.analyse.tweetsbytheme;

import be.ceau.chart.BarChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(TweetsbyTheme.class)
@ComponentScan(basePackages = { "ch.bfh.analyse.tweetsbytheme", "ch.bfh.controllers", "ch.bfh.repositories", "ch.bfh.util" })
public class TweetsbyThemeTest {

    @Autowired
    TweetsbyTheme tweetsbyTheme;

    @Test
    public void getName() throws Exception {
        assert this.tweetsbyTheme.getName().equalsIgnoreCase("countbytheme");
    }

    @Test
    public void getChart() throws Exception {
        assert (this.tweetsbyTheme.getChart().getClass() == BarChart.class);
    }

}