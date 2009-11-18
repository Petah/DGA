/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */


package dataanalyzer.entity;

import dataanalyzer.exception.InvalidDataTypeException;
import java.io.Serializable;

/**
 * This class implements the Data class and represents a Integer unit of data.
 * @author David Neilsen
 */
public class DataInt implements Data<Integer>, Serializable {

    /** The unit of data */
    Integer i;

    /**
     * Initializes the data unit to null.
     */
    public DataInt() {
    }

    /**
     * Initializes the data unit to the supplied value.
     * @param b the value of the data unit
     */
    public DataInt(Integer b) {
        this.i = b;
    }

    public Integer get() {
        return i;
    }

    public void set(Integer u) {
        i = u;
    }

    @Override
    public String toString() {
        return Integer.toString(i);
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
        return (int) i;
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
        return true;
    }

    public boolean isLong() {
        return true;
    }
}