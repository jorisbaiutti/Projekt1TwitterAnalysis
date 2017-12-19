package ch.bfh.analyse.languageanalyse;

import be.ceau.chart.BarChart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(TweetsbyLanguage.class)
@ComponentScan(basePackages = { "ch.bfh.analyse.languageanalyse", "ch.bfh.controllers", "ch.bfh.repositories", "ch.bfh.util" })
public class TweetsbyLanguageTest {

    @Autowired
    TweetsbyLanguage tweetsbyLanguage;

    @Test
    public void getName() throws Exception {
        assert this.tweetsbyLanguage.getName().equalsIgnoreCase("countbylanguage");
    }

    @Test
    public void getChart(){ assert (this.tweetsbyLanguage.getChart().getClass() == BarChart.class); }

}