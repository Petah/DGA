/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

import dataanalyzer.gui.IAlgorithmPanel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author davnei06
 */
public class AlgorithmTest {

    public AlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createProcess method, of class Algorithm.
     */
    @Test
    public void testCreateProcess() {
        System.out.println("createProcess");
        Algorithm instance = new AlgorithmImpl();
        AlgorithmProcess expResult = null;
        AlgorithmProcess result = instance.createProcess();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Algorithm.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Algorithm instance = new AlgorithmImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dispose method, of class Algorithm.
     */
    @Test
    public void testDispose() {
        System.out.println("dispose");
        Algorithm instance = new AlgorithmImpl();
        instance.dispose();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGUI method, of class Algorithm.
     */
    @Test
    public void testGetGUI() {
        System.out.println("getGUI");
        Algorithm instance = new AlgorithmImpl();
        IAlgorithmPanel expResult = null;
        IAlgorithmPanel result = instance.getGUI();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of collectInput method, of class Algorithm.
     */
    @Test
    public void testCollectInput() {
        System.out.println("collectInput");
        Object o = null;
        Algorithm instance = new AlgorithmImpl();
        instance.collectInput(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Algorithm.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Algorithm instance = new AlgorithmImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AlgorithmImpl extends Algorithm {

        public AlgorithmProcess createProcess() {
            return null;
        }

        public String getName() {
            return "";
        }
    }

}