package dataanalyzer.entity;

import dataanalyzer.exception.InvalidDataTypeException;
import java.io.Serializable;

/**
 * This class implements the Data class and represents a Byte unit of data.
 * @author David Neilsen
 */
public class DataByte implements Data<Byte>, Serializable {

    /** The unit of data */
    Byte b;

    /**
     * Initializes the data unit to null.
     */
    public DataByte() {
    }

    /**
     * Initializes the data unit to the supplied value.
     * @param b the value of the data unit
     */
    public DataByte(Byte b) {
        this.b = b;
    }

    public Byte get() {
        return b;
    }

    public void set(Byte u) {
        b = u;
    }

    @Override
    public String toString() {
        return Double.toString(b);
    }

    public Byte getByte() throws InvalidDataTypeException {
        return b;
    }

    public Double getDouble() throws InvalidDataTypeException {
        return (double) b;
    }

    public Float getFloat() throws InvalidDataTypeException {
        return (float) b;
    }

    public Integer getInteger() throws InvalidDataTypeException {
        return (int) b;
    }

    public Long getLong() throws InvalidDataTypeException {
        return (long) b;
    }

    public boolean isByte() {
        return true;
    }

    public boolean isDouble() {
        return true;
    }

    public boolean isFloat() {
        return true;
    }

    public boolean isInteger() {
        return true;
    }

    public boolean isLong() {
        return true;
    }
}
