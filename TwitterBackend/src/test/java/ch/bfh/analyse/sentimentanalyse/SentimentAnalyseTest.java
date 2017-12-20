package ch.bfh.analyse.sentimentanalyse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(SentimentAnalyse.class)
@ComponentScan(basePackages = { "ch.bfh.analyse.sentimentanalyse", "ch.bfh.categorizer", "ch.bfh.controllers", "ch.bfh.repositories", "ch.bfh.util" })
public class SentimentAnalyseTest {

    @Autowired
    SentimentAnalyse sentimentAnalyse;

    @Test
    public void returnTheRightAnalyseName() throws Exception {
        assert this.sentimentAnalyse.getName().equalsIgnoreCase("sentimentanalyse");
    }

    @Test
    public void positiveAndNegativeShouldBeHundredPercent() throws Exception {
        assert (this.sentimentAnalyse.getSentimentValueNegativeinPercent() + this.sentimentAnalyse.getSentimentValuePositiveinPercent()) == 100;
    }

    @Test
    public void shouldBeBetweenZeroAndMaxValue() throws Exception {
        assert this.sentimentAnalyse.getSentimentValue() >= 0 && this.sentimentAnalyse.getSentimentValue() <= this.sentimentAnalyse.getMaxValue();
    }

}