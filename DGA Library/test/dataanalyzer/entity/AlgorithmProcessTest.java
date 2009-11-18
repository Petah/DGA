/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

import dataanalyzer.interfaces.ISystem;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.listeners.OutputListener;
import dataanalyzer.listeners.ProcessListener;
import dataanalyzer.listeners.ProgressListener;
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
public class AlgorithmProcessTest {

    public AlgorithmProcessTest() {
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
     * Test of run method, of class AlgorithmProcess.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class AlgorithmProcess.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cycle method, of class AlgorithmProcess.
     */
    @Test
    public void testCycle() {
        System.out.println("cycle");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        boolean expResult = false;
        boolean result = instance.cycle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class AlgorithmProcess.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        Output expResult = null;
        Output result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AlgorithmProcess.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createListeners method, of class AlgorithmProcess.
     */
    @Test
    public void testCreateListeners() {
        System.out.println("createListeners");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.createListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendOutput method, of class AlgorithmProcess.
     */
    @Test
    public void testSendOutput_Object() {
        System.out.println("sendOutput");
        Object object = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.sendOutput(object);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendOutput method, of class AlgorithmProcess.
     */
    @Test
    public void testSendOutput_Object_String() {
        System.out.println("sendOutput");
        Object object = null;
        String name = "";
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.sendOutput(object, name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendProcess method, of class AlgorithmProcess.
     */
    @Test
    public void testSendProcess() {
        System.out.println("sendProcess");
        AlgorithmProcess algorithmProcess = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.sendProcess(algorithmProcess);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of progressUpdate method, of class AlgorithmProcess.
     */
    @Test
    public void testProgressUpdate() {
        System.out.println("progressUpdate");
        int value = 0;
        String label = "";
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.progressUpdate(value, label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCompletionListener method, of class AlgorithmProcess.
     */
    @Test
    public void testAddCompletionListener() {
        System.out.println("addCompletionListener");
        CompletionListener c = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.addCompletionListener(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addProgressListener method, of class AlgorithmProcess.
     */
    @Test
    public void testAddProgressListener() {
        System.out.println("addProgressListener");
        ProgressListener p = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.addProgressListener(p);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAlgorithmProcessListener method, of class AlgorithmProcess.
     */
    @Test
    public void testAddAlgorithmProcessListener() {
        System.out.println("addAlgorithmProcessListener");
        ProcessListener a = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.addAlgorithmProcessListener(a);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addOutputListener method, of class AlgorithmProcess.
     */
    @Test
    public void testAddOutputListener() {
        System.out.println("addOutputListener");
        OutputListener o = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.addOutputListener(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutputName method, of class AlgorithmProcess.
     */
    @Test
    public void testSetOutputName() {
        System.out.println("setOutputName");
        String outputName = "";
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.setOutputName(outputName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystem method, of class AlgorithmProcess.
     */
    @Test
    public void testSetSystem() {
        System.out.println("setSystem");
        ISystem system = null;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.setSystem(system);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class AlgorithmProcess.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AlgorithmProcess.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        AlgorithmProcess instance = new AlgorithmProcessImpl();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AlgorithmProcessImpl extends AlgorithmProcess {

        public boolean cycle() {
            return false;
        }
    }

}