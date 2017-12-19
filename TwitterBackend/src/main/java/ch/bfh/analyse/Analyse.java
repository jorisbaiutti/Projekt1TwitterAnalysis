package ch.bfh.analyse;

import be.ceau.chart.Chart;

public interface Analyse<T extends Chart> {
     T getChart();
     String getName();
}
