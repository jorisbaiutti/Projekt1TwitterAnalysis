package ch.bfh.analyse;

import ch.bfh.analyse.sentimentanalyse.SentimentAnalyse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
