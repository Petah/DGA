/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package huffman;

import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Data;
import dataanalyzer.entity.DataByte;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.exception.InvalidDataTypeException;
import huffman.util.HuffmanDecoder;
import huffman.util.HuffmanEncoder;
import huffman.util.HuffmanUtil;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author davnei06
 */
public class HuffmanCompressProcess extends AlgorithmProcess {

    private HuffmanEncoder encoder;
    private String rawData;

    public HuffmanCompressProcess(SequenceData sequenceData) {
        byte[] byteArray = new byte[sequenceData.size()];
        int i = 0;
        for (Data data : sequenceData.getAll()) {
            try {
                byteArray[i++] = data.getByte();
            } catch (InvalidDataTypeException ex) {
                system.handleException(ex);
            }
        }
        this.rawData = new String(byteArray);
        encoder = new HuffmanEncoder();
    }

    @Override
    public boolean cycle() {
        Hashtable<Character, String> huffEncodeTable;
        huffEncodeTable = new Hashtable<Character, String>();
        ArrayList<Byte> binaryEncodedData = encoder.encode(rawData, huffEncodeTable);
        SequenceData sequenceData = new SequenceData();
        for (Byte b : binaryEncodedData) {
            DataByte data = new DataByte(b);
            sequenceData.addSingle(data);
        }
        output = sequenceData;
        return false;
    }

    public static void main(String[] args) {
        byte[] byteArray = new byte[]{87, 79, 87, 46, 46, 46};

        String value = new String(byteArray);

        System.out.println(value);

        Hashtable<Character, String> huffEncodeTable;

        String rawData = "AAAAABBBBCCCDDE";

        System.out.println("Raw Data");
        HuffmanUtil.display48(rawData);

        int rawDataLen = rawData.length();

        System.out.println("nNumber raw data bits: " + rawData.length() * 8);

        HuffmanEncoder encoder = new HuffmanEncoder();

        huffEncodeTable = new Hashtable<Character, String>();
        ArrayList<Byte> binaryEncodedData = encoder.encode(rawData, huffEncodeTable);

        System.out.println("Number binary encoded data bits: " + binaryEncodedData.size() * 8);
        System.out.println("Compression factor: " + (double) rawData.length() / binaryEncodedData.size());

        System.out.println("nBinary Encoded Data in Hexadecimal Format");
        HuffmanUtil.hexDisplay48(binaryEncodedData);
        System.out.println();

        HuffmanDecoder decoder = new HuffmanDecoder();

        String decodedData = decoder.decode(binaryEncodedData,
                huffEncodeTable,
                rawDataLen);

        System.out.println("nDecoded Data");
        HuffmanUtil.display48(decodedData);

    }
}

