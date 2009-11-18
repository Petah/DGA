/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package compression.util;

import dataanalyzer.entity.Data;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author davnei06
 */
public class SequenceDataInputStream extends InputStream {

    private SequenceData data;
    private int count;

    public SequenceDataInputStream(SequenceData data) {
        this.data = data;
        this.count = 0;
    }

    @Override
    public int read() throws IOException {
        if (count == data.size()) {
            return -1;
        }
        Data d = data.getSingle(count++);
        try {
            byte b = d.getByte();
            //System.out.println("R" + b);
            //Convert to signed byte (because that is the Java standard)
            return ((int) b) + 128;
        } catch (InvalidDataTypeException ex) {
            throw new IOException(ex);
        }
    }
}
