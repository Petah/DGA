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

import dataanalyzer.ApplicationInterface;

/**
 *
 * @author Petah
 */
public class Main {
    public static void main(String[] args) {
        ApplicationInterface applicationInterface = new ApplicationInterface();
        applicationInterface.addAlgorithm(new GameAlgorithm());
    }
}
