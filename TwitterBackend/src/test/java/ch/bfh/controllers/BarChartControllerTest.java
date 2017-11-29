package ch.bfh.controllers;

import ch.bfh.analyse.sampleanalyse.SampleAnalyse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BarChartController.class)
public class BarChartControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleAnalyse service;

    @Test
    public void shouldreturnaListofanalyse() throws Exception {
        this.mockMvc.perform(get("/api/barchart/list")).andDo(print()).andExpect(status().isOk());
    }
}