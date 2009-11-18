/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.UserInterfaceTest;
import dataanalyzer.UserInterfaceTest.UserInterfaceImpl;
import dataanalyzer.entity.Algorithm;
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
public class AlgorithmManagerTest {

    private static DGA system;
    private static Algorithm algorithm;

    public AlgorithmManagerTest() {
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
        algorithm =  new Algorithm() {

            @Override
            public AlgorithmProcess createProcess() {
                return new AlgorithmProcess() {

                    @Override
                    public boolean cycle() {
                        return false;
                    }
                };
            }

            @Override
            public String getName() {
                return "Test algorithm";
            }
        };
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class AlgorithmManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Algorithm a = algorithm;
        AlgorithmManager instance = system.algorithmManager;
        instance.add(a);
        assertEquals(instance.get(0), a);
    }

    /**
     * Test of get method, of class AlgorithmManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        AlgorithmManager instance = system.algorithmManager;
        Algorithm a = algorithm;
        instance.add(a);
        ArrayList expResult = new ArrayList();
        expResult.add(a);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class AlgorithmManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int i = 0;
        AlgorithmManager instance = system.algorithmManager;
        Algorithm a = algorithm;
        instance.add(a);
        Algorithm expResult = a;
        Algorithm result = instance.get(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class AlgorithmManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        AlgorithmManager instance = system.algorithmManager;
        Algorithm a = algorithm;
        instance.add(a);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class AlgorithmManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        AlgorithmManager instance = system.algorithmManager;
        Algorithm a = algorithm;
        instance.add(a);
        instance.remove(a);
        assertEquals(instance.size(), 0);
    }

    /**
     * Test of toString method, of class AlgorithmManager.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
    }
}
