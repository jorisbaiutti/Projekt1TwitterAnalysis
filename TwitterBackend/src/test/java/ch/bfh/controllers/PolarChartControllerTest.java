package ch.bfh.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PolarChartController.class)
@ComponentScan(basePackages = { "ch.bfh" })
public class PolarChartControllerTest {

    @Autowired
    PolarChartController polarChartController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldreturnaListofanalyse() throws Exception {
        List<String> availableendpoints = polarChartController.analysen.stream().map(a -> a.getName()).collect(Collectors.toList());
        String responsebody = availableendpoints.toString();


        this.mockMvc.perform(get("/api/polarchart/list"))
                .andDo(print())
                .andExpect(status().is(200))
                .andExpect(content().json(responsebody));
    }

    @Test
    public void shouldreturnPolarCharts() throws Exception{

    }
}