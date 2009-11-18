/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lzw;

import compression.util.CompressionTask;
import compression.util.SequenceDataInputStream;
import compression.util.SequenceDataOutputStream;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.SequenceData;

/**
 *
 * @author davnei06
 */
public class LZWProcess extends AlgorithmProcess {

    private SequenceData inData;
    private CompressionTask type;

    public LZWProcess(SequenceData inData, CompressionTask type) {
        this.inData = inData;
        this.type = type;
        setOutputName("LZW");
    }

    @Override
    public boolean cycle() {
        try {
            lzw.util.LZW lzw = new lzw.util.LZW();
            SequenceDataInputStream in = new SequenceDataInputStream(inData);
            SequenceDataOutputStream out = new SequenceDataOutputStream();
            //FileOutputStream out = new FileOutputStream(outFile);
            switch (type) {
                case COMPRESS: {
                    lzw.compress(in, out);
                    output = out.getData();
                    break;
                }
                case DECOMPRESS: {
                    //throw new UnsupportedOperationException("Not supported yet.");
                    lzw.decompress(in, out);
                    output = out.getData();
                    break;
                }
            }
            in.close();
            out.close();
        } catch (Exception ex) {
            system.handleException(ex);
        }
        return false;
    }
}