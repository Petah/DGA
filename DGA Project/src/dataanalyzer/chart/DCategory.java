package dataanalyzer.chart;

import dataanalyzer.entity.Data;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.OutputCollection;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * This class creates a CategoryDataset from an Output object. Valid ouput can only
 * be an instance of OutputCollection or SequenceData.
 * @author David Neilsen
 */
public class DCategory {

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
     * Initializes instance variables.
     * @param scale the amount of data to display from the SequenceData 
     * @param offset the position to start displaying data from the SequenceData 
     * @param step how many places to move each step
     * @param seriesCount the index of the output if the output is a collection
     * @param output the output to include in the DataSet
     */
    public DCategory(int scale, int offset, int step, int seriesCount, Output output) {
        this.scale = scale;
        this.offset = offset;
        this.step = step;
        this.seriesCount = seriesCount;
        this.output = output;
    }

    /**
     * Recursive method to create the DataSet categorys. A DataSet is created for
     * each SequenceData in the OutputCollection.
     * @param dataset the DefaultCategoryDataset to add new categorys to
     * @param output the output to put into the DataSet
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be added to the DataSet
     */
    private void createDataSet(DefaultCategoryDataset dataset, Output output) throws InvalidDataTypeException {
        if (output.getOutput() instanceof OutputCollection) {
            OutputCollection outputCollection = (OutputCollection) output.getOutput();
            for (Output o : outputCollection.getAll()) {
                createDataSet(dataset, o);
            }
        } else if (output.getOutput() instanceof SequenceData) {
            SequenceData sequence = (SequenceData) output.getOutput();
            createCategory(dataset, sequence, output.toString() + seriesCount++);
        } else {
            throw new InvalidDataTypeException("Data sequence not supported by this data set.");
        }
    }

    /**
     * Creates a category from a SequenceData object and adds it to the DataSet.
     * @param dataset the DataSet to add the new category to
     * @param sequence the SequenceData to create the category
     * @param name the name of the category
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be added to the DataSet
     */
    private void createCategory(DefaultCategoryDataset dataset, SequenceData sequence, String name) throws InvalidDataTypeException {
        for (int i = offset; i < offset + scale; i += step) {
            //To prevent errors when using 2 sequences of different sizes
            if (i >= sequence.size()) {
                break;
            }
            Data d = sequence.getSingle(i);
            dataset.addValue(d.getDouble(), name, Integer.toString(i));
        }
    }

    /**
     * Creates a CategoryDataset.
     * @return an instance of CategoryDataset
     * @throws dataanalyzer.exception.InvalidDataTypeException if output contains
     * data that cannot be added to the DataSet
     */
    public CategoryDataset createCategoryDataset() throws InvalidDataTypeException {
        // Create area chart dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        createDataSet(dataset, output);
        return dataset;
    }
}
