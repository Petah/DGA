package dataanalyzer.chart;

import dataanalyzer.entity.Output;
import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.chart.JFreeChart;

/**
 * A generalized class to create a JFreeChart.
 * @author David Neilsen
 */
public abstract class IChart {

    /** The amount of data to show (in units) */
    protected int scale;
    /** The position of the data sequence to start showing data */
    protected int offset;
    /** The amount of data to skip each step */
    protected int step;
    /** The number of the output if the output is a collection */
    protected int seriesCount;
    /** The output to show in the chart */
    protected Output output;

    /**
     * Creates an instance of JFreeChart. The type of chart depends on the impelmenting 
     * class.
     * @return an instance of JFreeChart
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be graphed
     */
    public abstract JFreeChart create() throws InvalidDataTypeException;

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setStep(int step) {
        this.step = step;
    }
}