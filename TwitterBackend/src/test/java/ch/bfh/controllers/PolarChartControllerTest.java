package ch.bfh.controllers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PolarChartController.class)
@ComponentScan(basePackages = { "ch.bfh" })
public class PolarChartControllerTest {

     /*No Analyzes yet*/
}