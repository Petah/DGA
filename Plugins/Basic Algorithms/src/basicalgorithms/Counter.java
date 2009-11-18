/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package basicalgorithms;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.Serializable;

/**
 *
 * @author Petah
 */
public class Counter extends Algorithm implements Serializable {

//    private Long count;
//    private Long multiplier;
    private Long time = new Long(2000);
//    private Long endTime;
//    private Long startTime;
//    private Output output;

    @Override
    public IAlgorithmPanel getGUI() {
        return new PCounter(this);
    }

    @Override
    public void collectInput(Object o) {
        if (o instanceof Long) {
            if (time == null) {
                time = (Long) o;
            }
        }
    }

    @Override
    public AlgorithmProcess createProcess() {
        CounterProcess process = new CounterProcess(time);
        process.init();
        return process;
    }

//    @Override
//    public Output getOutput() {
//        return output;
//    }

//    @Override
//    public void init() {
//        process = new CounterProcess(time);
////        count = 0l;
////        endTime = System.currentTimeMillis() + time * 1000;
////        startTime = System.currentTimeMillis();
////        multiplier = 0l;
//    }

//    @Override
//    public boolean cycle() {
//        count++;
//        long elapsed = System.currentTimeMillis() - startTime;
//        int progress = (int) Math.ceil((double) elapsed / (double) (time * 1000) * (double) 100);
//        progressUpdate(progress, "Counting");
//        if (count == Long.MAX_VALUE) {
//            multiplier++;
//            count = 0l;
//        }
//        if (System.currentTimeMillis() >= endTime) {
//            createOutput();
//            return false;
//        }
//        return true;
//    }


    @Override
    public void dispose() {
//        count = time = endTime = startTime = null;
        time = new Long(2000);
    }

    @Override
    public String getName() {
        return "Counter";
    }
}
class CounterProcess extends AlgorithmProcess {

    private Long count;
    private Long multiplier;
    private Long time;
    private Long endTime;
    private Long startTime;

    public CounterProcess(Long time) {
        this.time = time;
        setOutputName("Counter");
    }
    public void init() {
        count = 0l;
        endTime = System.currentTimeMillis() + time * 1000;
        startTime = System.currentTimeMillis();
        multiplier = 0l;
    }
    public boolean cycle() {
        count++;
        long elapsed = System.currentTimeMillis() - startTime;
        int progress = (int) Math.ceil((double) elapsed / (double) (time * 1000) * (double) 100);
        progressUpdate(progress, "Counting");
        if (count == Long.MAX_VALUE) {
            multiplier++;
            count = 0l;
        }
        if (System.currentTimeMillis() >= endTime) {
            createOutput();
            return false;
        }
        return true;
    }
    
    public void createOutput() {
        String o = "Number of increases in " + time + " seconds: ";
        if (multiplier > 0) {
            o += count + " + " + multiplier + " * " + Long.MAX_VALUE;
        } else {
            o += count;
            o += "\nAverage per second: " + count / time;
        }
        output = o;
    }
}