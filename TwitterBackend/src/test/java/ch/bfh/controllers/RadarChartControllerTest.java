package ch.bfh.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(RadarChartController.class)
@ComponentScan(basePackages = { "ch.bfh" })
public class RadarChartControllerTest {

     /*No Analyzes yet*/
}