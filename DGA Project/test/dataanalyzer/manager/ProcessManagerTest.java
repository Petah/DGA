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
public class ProcessManagerTest {

    private static DGA system;
    private static AlgorithmProcess algorithmProcess;

    public ProcessManagerTest() {
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
        algorithmProcess = new AlgorithmProcess() {

            @Override
            public boolean cycle() {
                return false;
            }
        };
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of collectAlgorithmProcess method, of class ProcessManager.
     */
    @Test
    public void testCollectAlgorithmProcess() {
        System.out.println("collectAlgorithmProcess");
        ProcessManager instance = system.processManager;
        instance.collectAlgorithmProcess(algorithmProcess);
        assertEquals(system.processManager.size(), 1);
    }

    /**
     * Test of add method, of class ProcessManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        AlgorithmProcess p = algorithmProcess;
        ProcessManager instance = system.processManager;
        instance.add(p);
        assertEquals(system.processManager.get(0), p);
    }

    /**
     * Test of get method, of class ProcessManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        AlgorithmProcess p = algorithmProcess;
        ProcessManager instance = system.processManager;
        instance.add(p);
        ArrayList expResult = new ArrayList();
        expResult.add(p);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class ProcessManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        AlgorithmProcess u = algorithmProcess;
        ProcessManager instance = system.processManager;
        instance.add(u);
        instance.remove(u);
        assertEquals(system.processManager.size(), 0);
    }

    /**
     * Test of get method, of class ProcessManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        AlgorithmProcess u = algorithmProcess;
        ProcessManager instance = system.processManager;
        instance.add(u);
        assertEquals(system.processManager.get(0), u);
    }

    /**
     * Test of size method, of class ProcessManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        AlgorithmProcess u = algorithmProcess;
        ProcessManager instance = system.processManager;
        instance.add(u);
        assertEquals(1, system.processManager.size());
    }
}
