/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.AlgorithmProcess;
import dataanalyzer.entity.Output;
import dataanalyzer.listeners.SystemListener;
import java.util.ArrayList;
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
public class OutputManagerTest {

    private static DGA system;
    private static Output output;
    public OutputManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        system = new DGA(new UserInterface() {

            public void handleException(Throwable ex) {
            }

            public void handleError(String s) {
            }

            public void out(Object o) {
            }

            public void collectOutput(SystemListener systemListener) {
            }

            public void sendOutput(Output o) {
            }
        });
        output = new Output(new String("test output"), new String("test owner"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class OutputManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Output o = output;
        OutputManager instance = system.outputManager;
        instance.add(o);
        assertEquals(o, instance.get(0));
    }

    /**
     * Test of remove method, of class OutputManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Output o = output;
        OutputManager instance = system.outputManager;
        instance.add(o);
        instance.remove(o);
        assertEquals(0, instance.size());
    }

    /**
     * Test of get method, of class OutputManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        Output o = output;
        OutputManager instance = system.outputManager;
        instance.add(o);
        ArrayList expResult = new ArrayList();
        expResult.add(o);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class OutputManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int i = 0;
        Output o = output;
        OutputManager instance = system.outputManager;
        instance.add(o);
        Output expResult = o;
        Output result = instance.get(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class OutputManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Output o = output;
        OutputManager instance = system.outputManager;
        instance.add(o);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of complete method, of class OutputManager.
     */
    @Test
    public void testComplete() {
        System.out.println("complete");
        OutputManager instance = system.outputManager;
        AlgorithmProcess algorithmProcess = new AlgorithmProcess() {

            @Override
            public boolean cycle() {
                this.setOutputName("test process");
                this.output = new String("test output");
                return false;
            }
        };
        algorithmProcess.cycle();
        instance.complete(algorithmProcess);
        assertEquals(algorithmProcess.getOutput().getOutput(), instance.get(0).getOutput());
        assertEquals(algorithmProcess.getOutput().getOwner(), instance.get(0).getOwner());
        assertEquals(algorithmProcess.getOutput().toString(), instance.get(0).toString());
    }

    /**
     * Test of toString method, of class OutputManager.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
    }

}