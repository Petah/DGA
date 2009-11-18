/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package standard;

import compression.util.SequenceDataInputStream;
import compression.util.SequenceDataOutputStream;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.SequenceData;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author davnei06
 */
public class InflaterProcess extends AlgorithmProcess {

    private Inflater inflater;
    private InflaterInputStream inflaterInputStream;
    private SequenceDataOutputStream dataOutputStream;
    private SequenceDataInputStream dataInputStream;
    private int data;

    public InflaterProcess(SequenceData sequenceData) {
        dataOutputStream = new SequenceDataOutputStream();
        dataInputStream = new SequenceDataInputStream(sequenceData);
        inflater = new Inflater();
        inflaterInputStream = new InflaterInputStream(dataInputStream, inflater);
        setOutputName("Inflater");
    }

    @Override
    public boolean cycle() {
        try {
            data = inflaterInputStream.read();
            if (data != -1) {
                dataOutputStream.write(data);
                return true;
            } else {
                output = dataOutputStream.getData();
                inflaterInputStream.close();
                dataOutputStream.close();
                dataInputStream.close();
            }
        } catch (IOException ex) {
            system.handleException(ex);
        }
        return false;
    }

}
