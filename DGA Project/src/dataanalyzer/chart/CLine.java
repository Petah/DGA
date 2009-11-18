package dataanalyzer.chart;

import dataanalyzer.entity.Output;
import dataanalyzer.entity.OutputCollection;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;

/**
 * Creates a Line Chart.
 * @author David Neilsen
 */
// TODO: Move the creating of an XYDataSet into its own class
public class CLine extends IChart {

    /** The title of the graph */
    private String title = "Sequence Line Graph";
    /** The x axis label */
    private String xAxis = "Position";
    /** The y axis label */
    private String yAxis = "Value";
    /** A flag specifying whether or not a legend is required */
    private boolean legend = true;
    /** Configure chart to generate tool tips? */
    private boolean tooltips = true;
    /** Configure chart to generate URLs? */
    private boolean urls = true;

    /**
     * Default constructor.
     */
    public CLine() {
    }

    /**
     * Initializes the Chart variables.
     * @param title the title of the graph
     * @param xAxis the x axis label
     * @param yAxis the y axis label
     * @param legend a flag specifying whether or not a legend is required
     * @param tooltips configure chart to generate tool tips?
     * @param urls configure chart to generate URLs?
     */
    public CLine(String title, String xAxis, String yAxis, boolean legend, boolean tooltips, boolean urls) {
        this.title = title;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        this.legend = legend;
        this.tooltips = tooltips;
        this.urls = urls;
    }

    /**
     * Creates a line chart.
     * @return the line chart, an instance of JFreeChart
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    public JFreeChart create() throws InvalidDataTypeException {
        return ChartFactory.createXYLineChart(
                title, xAxis, yAxis,
                createXYDataset(), PlotOrientation.VERTICAL,
                legend, tooltips, urls);
    }

    /**
     * Creates an array of values and x/y points to create the line graph from.
     * @param data the SequenceData to graph
     * @return an array that can be passed to a DataSet
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    public double[][] createArray(SequenceData data) throws InvalidDataTypeException {
        //Prevent divide by errors causing array index out of bounds
        int size = (int) Math.ceil((double) scale / (double) step);
        //Create array of points
        double[][] points = new double[2][size];
        int count = 0;
        //Start looping from the offset for the amount specified in scale
        for (int i = offset; i < offset + scale; i += step) {
            //To prevent errors when using 2 sequences of different sizes
            if (i >= data.size()) {
                points[0][count] = i;
                points[1][count] = 0;
            } else {
                //Add the data to the array (at the count position)
                points[0][count] = i;
                points[1][count] = data.getSingle(i).getDouble();
            }
            count++;
        }
        return points;
    }

    /**
     * Creates a DefaultXYDataset from an Output instance.
     * @param dataset the DefaultXYDataset to append series to
     * @param output the output to graph
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    private void createDataSet(DefaultXYDataset dataset, Output output) throws InvalidDataTypeException {
        if (output.getOutput() instanceof OutputCollection) {
            OutputCollection outputCollection = (OutputCollection) output.getOutput();
            for (Output o : outputCollection.getAll()) {
                createDataSet(dataset, o);
            }
        } else if (output.getOutput() instanceof SequenceData) {
            SequenceData sequence = (SequenceData) output.getOutput();
            dataset.addSeries(output.toString() + seriesCount++, createArray(sequence));
        } else {
            throw new InvalidDataTypeException("Data sequence not supported by this data set.");
        }

    }

    /**
     * Creates a XYDataset.
     * @return the XYDataset
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    private XYDataset createXYDataset() throws InvalidDataTypeException {
        seriesCount = 1;
        DefaultXYDataset dataset = new DefaultXYDataset();
        createDataSet(dataset, output);
        return dataset;
    }

    @Override
    public String toString() {
        return "line chart";
    }
}
