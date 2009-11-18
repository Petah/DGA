package pseudorandom;

import dataanalyzer.entity.Algorithm;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.DataByte;
import dataanalyzer.entity.Output;
import java.util.Random;
import dataanalyzer.entity.SequenceData;
import dataanalyzer.gui.IAlgorithmPanel;
import java.io.Serializable;

public class PseudoRandom extends Algorithm implements Serializable {

    private Integer min = new Integer(1);
    private Integer max = new Integer(1000);
    private Integer minLimit = new Integer(-100);
    private Integer maxLimit = new Integer(100);
    private Integer seed = new Integer(0);
    private Integer step = new Integer(1);

    @Override
    public IAlgorithmPanel getGUI() {
        return new PPseudoRandom(this);
    }

    @Override
    public void collectInput(Object o) {
        if (o instanceof Integer) {
            if (min == null) {
                min = (Integer) o;
            } else if (max == null) {
                max = (Integer) o;
            } else if (seed == null) {
                seed = (Integer) o;
            } else if (step == null) {
                step = (Integer) o;
            } else if (minLimit == null) {
                minLimit = (Integer) o;
            } else if (maxLimit == null) {
                maxLimit = (Integer) o;
            }
        }
    }

    @Override
    public AlgorithmProcess createProcess() {
        return new PseudoRandomProcess(min, max, minLimit, maxLimit, seed, step);
    }


    @Override
    public void dispose() {
        min = max = step = seed = minLimit = maxLimit = null;
    }

    public String getName() {
        return "PseudoRandom";
    }
}
class PseudoRandomProcess extends AlgorithmProcess {
    
    private Integer min;
    private Integer max;
    private Integer minLimit;
    private Integer maxLimit;
    private Integer seed;
    private Integer step;
    private Random random = new Random();
    private SequenceData sequence = new SequenceData();
    private int count;

    public PseudoRandomProcess(Integer min, Integer max, Integer minLimit, Integer maxLimit, Integer seed, Integer step) {
        this.min = min;
        this.max = max;
        this.minLimit = minLimit;
        this.maxLimit = maxLimit;
        this.seed = seed;
        this.step = step;
        count = min;
        setOutputName("Pseudo Random");
    }

    @Override
    public boolean cycle() {
        if (count++ > max) {
            output = sequence;
            return false;
        }
        DataByte b = null;
        while (b == null || b.get() > maxLimit || b.get() < minLimit) {
            random.setSeed(seed);
            seed += step;
            byte[] bytes = new byte[1];
            random.nextBytes(bytes);
            b = new DataByte(bytes[0]);
        }
        sequence.addSingle(b);
        double elapsed = count - min;
        int progress = (int)Math.ceil(elapsed / (double)(max - min) * 100d);
        progressUpdate(progress, "Generating");
        return true;
    }
}