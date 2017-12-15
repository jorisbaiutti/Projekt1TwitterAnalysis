package ch.bfh.controllers;

import ch.bfh.analyse.tweetsbytheme.TweetsbyTheme;
import ch.bfh.repositories.TweetRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MapChartController.class)
@ComponentScan(basePackages = { "ch.bfh" })
public class MapChartControllerTest {

    @Autowired
    MapChartController mapChartController;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldreturnaListofanalyse() throws Exception {
        List<String> availableendpoints = mapChartController.analysen.stream().map(a -> a.getName()).collect(Collectors.toList());
        String responsebody = availableendpoints.toString();


        this.mockMvc.perform(get("/api/map/list"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().json(responsebody));
    }

    @Test
    public void shouldreturnMapcharts() throws Exception{
        String analyse = mapChartController.analysen.stream().map(a -> a.getName()).findFirst().get();
        this.mockMvc.perform(get("/api/map/"+ analyse))
                .andDo(print())
                .andExpect(status().is(200));
    }

}