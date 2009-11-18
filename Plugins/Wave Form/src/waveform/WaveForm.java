/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package waveform;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.DataDouble;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.Serializable;


/**
 *
 * @author Petah
 */
public class WaveForm extends Algorithm implements Serializable {

    private Type type = Type.SIN;
    private Double start = new Double(0.0);
    private Double end = new Double(10);
    private Double step = new Double(0.001);

    public enum Type {

        SIN, COS, TAN
    }

    @Override
    public IAlgorithmPanel getGUI() {
        return new PWaveForm(this);
    }

    @Override
    public void collectInput(Object o) {
        if (o instanceof Double) {
            if (start == null) {
                start = (Double) o;
            } else if (end == null) {
                end = (Double) o;
            } else if (step == null) {
                step = (Double) o;
            }
        } else if (o instanceof Type) {
            type = (Type) o;
        }
    }

    @Override
    public AlgorithmProcess createProcess() {
        return new WaveFormProcess(type, start, end, step);
    }

    @Override
    public void dispose() {
        start = end = step = null;
    }

    @Override
    public String getName() {
        return "WaveForm";
    }
}
class WaveFormProcess extends AlgorithmProcess {

    private WaveForm.Type type;
    private Double start;
    private Double end;
    private Double step;
    private Double count;
    private SequenceData sequence = new SequenceData();

    public WaveFormProcess(WaveForm.Type type, Double start, Double end, Double step) {
        this.type = type;
        this.start = start;
        this.end = end;
        this.step = step;
        count = start;
        setOutputName("Wave Form");
    }

    @Override
    public boolean cycle() {
        DataDouble d = new DataDouble();
        switch (type) {
            case SIN: {
                d.set(Math.sin(count));
                break;
            }
            case COS: {
                d.set(Math.cos(count));
                break;
            }
            case TAN: {
                d.set(Math.tan(count));
                break;
            }
        }
        sequence.addSingle(d);
        count += step;
        double elapsed = count - start;
        int progress = (int) Math.ceil(elapsed / (double) (end - start) * 100d);
        progressUpdate(progress, "Calculating");
        if (count >= end) {
            output = sequence;
            return false;
        }
        return true;
    }
}