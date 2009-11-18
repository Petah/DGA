package dataanalyzer.chart;

import dataanalyzer.entity.Data;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Creates a Pie Chart.
 * @author David Neilsen
 */
public class CPie extends IChart {

    /**
     * Creates a pie chart.
     * @return the pie chart, an instance of JFreeChart
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    public JFreeChart create() throws InvalidDataTypeException {
        return ChartFactory.createPieChart(
                "Sequence Pie Chart",
                createPieDataset(),
                false, false, false);
    }

    /**
     * Creates a DefaultPieDataset from the objects output.
     * @return the DefaultPieDataset created
     * @throws InvalidDataTypeException
     */
    private DefaultPieDataset createPieDataset() throws InvalidDataTypeException {
        DefaultPieDataset dataset = new DefaultPieDataset();
        if (output.getOutput() instanceof SequenceData) {
            SequenceData sequence = (SequenceData) output.getOutput();
            Integer i = 0;
            for (Data d : sequence.getAll()) {
                try {
                    Number n = dataset.getValue(d.getByte());
                    dataset.setValue(d.getByte(), n.longValue() + 1);
                } catch (Exception ex) {
                    dataset.setValue(d.getByte(), 1);
                }
                i++;
            }
        } else {
            throw new InvalidDataTypeException("Data sequence not supported by this data set.");
        }
        return dataset;
    }

    @Override
    public String toString() {
        return "pie chart";
    }
}