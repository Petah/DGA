/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

import java.io.File;
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
public class PluginTest {

    public PluginTest() {
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
     * Test of add method, of class Plugin.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Algorithm a = new Algorithm() {

            @Override
            public AlgorithmProcess createProcess() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getName() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
        Plugin instance = new Plugin(new File("."));
        instance.add(a);
        assertEquals(instance.get().get(0), a);
    }

    /**
     * Test of get method, of class Plugin.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        Algorithm a = new Algorithm() {

            @Override
            public AlgorithmProcess createProcess() {
                throw new UnsupportedOperationException("Not supported yet.");
            }

            @Override
            public String getName() {
                return "name";
            }
        };
        Plugin instance = new Plugin(new File("."));
        instance.add(a);
        ArrayList expResult = new ArrayList();
        expResult.add(a);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }
}