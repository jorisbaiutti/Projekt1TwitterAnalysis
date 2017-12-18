package ch.bfh.analyse.sentimentanalyse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = { "ch.bfh.analyse.sentimentanalyse", "ch.bfh.categorizer", "ch.bfh.controllers", "ch.bfh.repositories", "ch.bfh.util" })
public class SentimentAnalyseTest {

    @Autowired
    SentimentAnalyse sentimentAnalyse;

    @Test
    public void returnTheRightAnalyseName(){
        assert this.sentimentAnalyse.getName().equalsIgnoreCase("sentimentanalyse");
    }

    @Test
    public void positiveAndNegativeShouldBeHundredPercent(){
        assert (this.sentimentAnalyse.getSentimentValueNegativeinPercent() + this.sentimentAnalyse.getSentimentValuePositiveinPercent()) == 100;
    }

    @Test
    public void shouldBeBetweenZeroAndMaxValue(){
        assert this.sentimentAnalyse.getSentimentValue() >= 0 && this.sentimentAnalyse.getSentimentValue() <= this.sentimentAnalyse.getMaxValue();
    }

}