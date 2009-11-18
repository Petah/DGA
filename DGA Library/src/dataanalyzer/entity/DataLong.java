package dataanalyzer.entity;

import dataanalyzer.exception.InvalidDataTypeException;
import java.io.Serializable;

/**
 * This class implements the Data class and represents a Long unit of data.
 * @author David Neilsen
 */
public class DataLong implements Data<Long>, Serializable {

    /** The unit of data */
    Long i;

    /**
     * Initializes the data unit to null.
     */
    public DataLong() {
    }

    /**
     * Initializes the data unit to the supplied value.
     * @param b the value of the data unit
     */
    public DataLong(Long b) {
        this.i = b;
    }

    public Long get() {
        return i;
    }

    public void set(Long u) {
        i = u;
    }

    @Override
    public String toString() {
        return Double.toString(i);
    }

    public Byte getByte() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert integer to byte with out losing data.");
    }

    public Double getDouble() throws InvalidDataTypeException {
        return (double) i;
    }

    public Float getFloat() throws InvalidDataTypeException {
        return (float) i;
    }

    public Integer getInteger() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert integer to byte with out losing data.");
    }

    public Long getLong() throws InvalidDataTypeException {
        return (long) i;
    }

    public boolean isByte() {
        return false;
    }

    public boolean isDouble() {
        return true;
    }

    public boolean isFloat() {
        return true;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isLong() {
        return true;
    }
}
