package dataanalyzer.entity;

import dataanalyzer.exception.InvalidDataTypeException;
import java.io.Serializable;

/**
 * This class implements the Data class and represents a Double unit of data.
 * @author David Neilsen
 */
public class DataDouble implements Data<Double>, Serializable {

    /** The unit of data */
    Double d;

    /**
     * Initializes the data unit to null.
     */
    public DataDouble() {
    }

    /**
     * Initializes the data unit to the supplied value.
     * @param d the value of the data unit
     */
    public DataDouble(Double d) {
        this.d = d;
    }

    public Double get() {
        return d;
    }

    public void set(Double u) {
        d = u;
    }

    @Override
    public String toString() {
        return Double.toString(d);
    }

    public Byte getByte() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert double to byte with out losing data.");
    }

    public Double getDouble() throws InvalidDataTypeException {
        return d;
    }

    public Float getFloat() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert float to byte with out losing data.");
    }

    public Integer getInteger() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert double to integer with out losing data.");
    }

    public Long getLong() throws InvalidDataTypeException {
        throw new InvalidDataTypeException("Cannot convert double to long with out losing data.");
    }

    public boolean isByte() {
        return false;
    }

    public boolean isDouble() {
        return true;
    }

    public boolean isFloat() {
        return false;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isLong() {
        return false;
    }
}
