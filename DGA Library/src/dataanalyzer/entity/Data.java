package dataanalyzer.entity;

import dataanalyzer.exception.InvalidDataTypeException;
import java.io.Serializable;

/**
 * This interface is used to repersent one unit of data. The data type is defined
 * in the implementing class and provides methods to convert between data types.
 * When converting the implementing method can throw a InvalidDataTypeException
 * to indicate the data cannot be safely converted.
 * @author David Neilsen
 */
public interface Data<U> extends Serializable {

    /**
     * Gets the unit of data in the defined data type.
     * @return the unit of data
     */
    public U get();

    /**
     * Sets the unit of data in the defined data type.
     * @param u the value to set the data to
     */
    public void set(U u);

    /**
     * Returns if the data can be converted to a Byte
     * @return true if the data can be converted to a Byte
     */
    public boolean isByte();

    /**
     * Returns if the data can be converted to a Integer
     * @return true if the data can be converted to a Integer
     */
    public boolean isInteger();

    /**
     * Returns if the data can be converted to a Long
     * @return true if the data can be converted to a Long
     */
    public boolean isLong();

    /**
     * Returns if the data can be converted to a Float
     * @return true if the data can be converted to a Float
     */
    public boolean isFloat();

    /**
     * Returns if the data can be converted to a Double
     * @return true if the data can be converted to a Double
     */
    public boolean isDouble();

    /**
     * Converts the data to a Byte.
     * @return the converted value
     * @throws InvalidDataTypeException if the data cannot be converted
     */
    public Byte getByte() throws InvalidDataTypeException;

    /**
     * Converts the data to a Integer.
     * @return the converted value
     * @throws InvalidDataTypeException if the data cannot be converted
     */
    public Integer getInteger() throws InvalidDataTypeException;

    /**
     * Converts the data to a Long.
     * @return the converted value
     * @throws InvalidDataTypeException if the data cannot be converted
     */
    public Long getLong() throws InvalidDataTypeException;

    /**
     * Converts the data to a Float.
     * @return the converted value
     * @throws InvalidDataTypeException if the data cannot be converted
     */
    public Float getFloat() throws InvalidDataTypeException;

    /**
     * Converts the data to a Double.
     * @return the converted value
     * @throws InvalidDataTypeException if the data cannot be converted
     */
    public Double getDouble() throws InvalidDataTypeException;
}
