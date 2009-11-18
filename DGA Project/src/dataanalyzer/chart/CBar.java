package dataanalyzer.chart;

import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;

// FIXME: When a sequence which is shorter than another is loaded first, you canot see the end of the longer sequence in the graph
/**
 * Creates a Bar Chart.
 * @author David Neilsen
 */
public class CBar extends IChart {

    /**
     * Creates an instance of JFreeChart which is an bar chart using a category 
     * data set.
     * @return an instance of JFreeChart
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    public JFreeChart create() throws InvalidDataTypeException {
        // Create data set
        DCategory categoryDataSet = new DCategory(scale, offset, step, seriesCount, output);
        // Create bar chart
        return ChartFactory.createBarChart(
                "Sequence Bar Graph", "Position", "Value",
                categoryDataSet.createCategoryDataset(), PlotOrientation.VERTICAL,
                false, true, true);
    }

    @Override
    public String toString() {
        return "bar chart";
    }
}
