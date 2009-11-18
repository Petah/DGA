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
import java.io.Serializable;

/**
 *
 * @author Petah
 */
public class HelloWorld extends Algorithm implements Serializable {

    @Override
    public AlgorithmProcess createProcess() {
       return new AlgorithmProcess() {

            @Override
            public boolean cycle() {
                system.out(getName());
                return false;
            }
        };
    }

    @Override
    public String getName() {
        return "Hello world! test";
    }
}
