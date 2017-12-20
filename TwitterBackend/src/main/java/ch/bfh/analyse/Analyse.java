package ch.bfh.analyse;

import be.ceau.chart.Chart;

/**
 * @param <T>
 *     Generic Interface for Analysis
 */
public interface Analyse<T extends Chart> {
     T getChart();
     String getName();
}
