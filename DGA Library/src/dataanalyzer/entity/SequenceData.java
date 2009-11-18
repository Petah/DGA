package dataanalyzer.entity;

import dataanalyzer.interfaces.IInformation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * An implementation of the Sequence sequence class using the Data class as the unit
 * and an ArrayList<Data> as the container.
 * @author David Neilsen
 */
public class SequenceData extends Sequence<Data, ArrayList<Data>> implements Serializable, IInformation {

    ArrayList<Data> data = new ArrayList<Data>();
    
    @Override
    public void addMulti(ArrayList<Data> m) {
        data.addAll(m);
    }

    @Override
    public void addSingle(Data s) {
        data.add(s);
    }

    @Override
    public ArrayList<Data> getAll() {
        return data;
    }

    @Override
    public Iterator<Data> getIterator() {
        return data.iterator();
    }

    @Override
    public ArrayList<Data> getMulti(int start, int length) {
        ArrayList<Data> r = new ArrayList<Data>();
        for (int i = start; i < start + length; i++) {
            r.add(data.get(i));
        }
        return r;
    }

    @Override
    public Data getSingle(int i) {
        return data.get(i);
    }

    public void remove(int i) {
        data.remove(i);
    }
    
    @Override
    public int size() {
        return data.size();
    }

    public String getInformation() {
        String s = "SequenceData";
        s += "\nSize: " + data.size();
        return s;
    }

    @Override
    public String toString() {
        return "SequenceData" + data;
    }
}
