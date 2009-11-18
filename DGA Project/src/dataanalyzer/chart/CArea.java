package dataanalyzer.chart;

import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

/**
 * Creates an area chart.
 * @author David Neilsen
 */
public class CArea extends IChart {

    /**
     * Creates an instance of JFreeChart which is an area chart using a category 
     * data set.
     * @return an instance of JFreeChart
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    @Override
    public JFreeChart create() throws InvalidDataTypeException {
        // Create data set
        DCategory categoryDataSet = new DCategory(scale, offset, step, seriesCount, output);
        // Create area chart
        return ChartFactory.createAreaChart(
                "Sequence Area Chart", "Position", "Value",
                categoryDataSet.createCategoryDataset(), PlotOrientation.VERTICAL,
                false, true, true);
    }

    public String toString() {
        return "area chart";
    }
}
