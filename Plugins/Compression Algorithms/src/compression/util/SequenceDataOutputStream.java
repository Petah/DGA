/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package compression.util;

import dataanalyzer.entity.DataByte;
import dataanalyzer.entity.SequenceData;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author davnei06
 */
public class SequenceDataOutputStream extends OutputStream {

    private SequenceData data = new SequenceData();

    @Override
    public void write(int b) throws IOException {
        //Convert to signed byte (because that is the Java standard)
        b -= 128;
        DataByte i = new DataByte((byte) (b));
        data.addSingle(i);
    //System.out.println(b);
    }

    public SequenceData getData() {
        return data;
    }
}