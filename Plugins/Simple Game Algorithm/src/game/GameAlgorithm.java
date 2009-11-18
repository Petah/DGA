/*
 * Classname
 * 
 * Version information
 *
 * Date
 * 
 * Copyright notice
 */
package game;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.DataInt;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.SequenceData;

/**
 *
 * @author Petah
 */
public class GameAlgorithm extends Algorithm {

    @Override
    public AlgorithmProcess createProcess() {
        return new GameProcess();
    }

    @Override
    public String getName() {
        return "SimpleGame";
    }
}

class GameProcess extends AlgorithmProcess {

    FMain fMain;
    long startTime = System.currentTimeMillis();
    long time = System.currentTimeMillis() + 10000;

    @Override
    public boolean cycle() {
        if (fMain == null) {
            fMain = new FMain();
            fMain.setVisible(true);
        } else if (time < System.currentTimeMillis()) {
            System.out.println("test");
            SequenceData data = new SequenceData();
            for (Integer i : fMain.getGame().getPointsPerSecond()) {
                DataInt d = new DataInt(i);
                data.addSingle(d);
            }
            system.sendOutput(new Output(data, this, "PPS: " + (time - startTime)));
            time = System.currentTimeMillis() + 10000;
        }
        return true;
    }
}
