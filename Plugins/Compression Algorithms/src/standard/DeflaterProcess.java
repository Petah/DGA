/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package standard;

import compression.util.SequenceDataInputStream;
import compression.util.SequenceDataOutputStream;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.SequenceData;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

/**
 *
 * @author davnei06
 */
public class DeflaterProcess extends AlgorithmProcess {

    private Deflater deflater;
    private DeflaterOutputStream deflaterOutputStream;
    private SequenceDataOutputStream dataOutputStream;
    private SequenceDataInputStream dataInputStream;
    private int data;

    public DeflaterProcess(SequenceData sequenceData) {
        dataOutputStream = new SequenceDataOutputStream();
        dataInputStream = new SequenceDataInputStream(sequenceData);
        deflater = new Deflater();
        deflaterOutputStream = new DeflaterOutputStream(dataOutputStream, deflater);
        setOutputName("Deflater");
    }
//TODO: close all streams

    @Override
    public boolean cycle() {
        try {
            data = dataInputStream.read();
            if (data != -1) {
                deflaterOutputStream.write(data);
                return true;
            } else {
                output = dataOutputStream.getData();
                deflaterOutputStream.close();
                dataOutputStream.close();
                dataInputStream.close();
            }
        } catch (IOException ex) {
            system.handleException(ex);
        }
        return false;
    }

    //Testing
    public static void main(String[] args) {
        try {
            byte[] b = {
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,
                1, 2, 3, 4, 5, 6, 7, 8, 9,};
            System.out.println("raw: " + b.length + "/" + g(b));
            OutputCatcher catcher = new OutputCatcher();
            compressData(b, catcher);
            System.out.println("deflated: " + catcher.c.toArray().length);
            InputSender sender = new InputSender();
            sender.c = catcher.c;
            byte[] r = decompressData(sender);
            System.out.println("inflated: " + r.length + "/" + g(r));
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static String g(byte[] b) {
        String s = "";
        for (byte a : b) {
            s += a + ", ";
        }
        return s;
    }

    public static class OutputCatcher extends OutputStream {

        public ArrayList<Integer> c = new ArrayList<Integer>();

        @Override
        public void write(int b) throws IOException {
            c.add(b);
        }
    }

    public static class InputSender extends InputStream {

        public ArrayList<Integer> c = new ArrayList<Integer>();
        int i = 0;

        @Override
        public int read() throws IOException {
            if (i >= c.size()) {
                return -1;
            }
            return c.get(i++);
        }
    }

    public static void compressData(byte[] data, OutputStream out)
            throws IOException {
        Deflater d = new Deflater();
        DeflaterOutputStream dout = new DeflaterOutputStream(out, d);
        dout.write(data);
        dout.close();
    }

    public static byte[] decompressData(InputStream in)
            throws IOException {
        InflaterInputStream inflaterInputStream = new InflaterInputStream(in);
        ByteArrayOutputStream bout =
                new ByteArrayOutputStream(512);
        int b;
        while ((b = inflaterInputStream.read()) != -1) {
            bout.write(b);
        }
        inflaterInputStream.close();
        bout.close();
        return bout.toByteArray();
    }
}
