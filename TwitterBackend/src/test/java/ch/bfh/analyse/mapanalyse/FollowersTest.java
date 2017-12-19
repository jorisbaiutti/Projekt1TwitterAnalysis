package ch.bfh.analyse.mapanalyse;

import ch.bfh.chartsmodel.GoogleMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(Followers.class)
@ComponentScan(basePackages = { "ch.bfh" })
public class FollowersTest {

    @Autowired
    Followers followers;

    @Test
    public void getChart() throws Exception {
        assert (this.followers.getChart().getClass() == GoogleMap.class);
    }

    @Test
    public void getName() throws Exception {
        assert this.followers.getName().equalsIgnoreCase("myfollowers");
    }

}