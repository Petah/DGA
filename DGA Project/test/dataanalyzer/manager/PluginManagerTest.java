/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Plugin;
import dataanalyzer.listeners.SystemListener;
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
public class PluginManagerTest {

    private static DGA system;
    public PluginManagerTest() {
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
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class PluginManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Plugin p = new Plugin(new File("."));
        PluginManager instance = system.pluginManager;
        instance.add(p);
        assertTrue(instance.get().size() > 0);
    }

    /**
     * Test of get method, of class PluginManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        Plugin p = new Plugin(new File("."));
        PluginManager instance = system.pluginManager;
        instance.add(p);
        ArrayList expResult = new ArrayList();
        expResult.add(p);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class PluginManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int i = 0;
        Plugin p = new Plugin(new File("."));
        PluginManager instance = system.pluginManager;
        instance.add(p);
        Plugin expResult = p;
        Plugin result = instance.get(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of size method, of class PluginManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Plugin p = new Plugin(new File("."));
        PluginManager instance = system.pluginManager;
        instance.add(p);
        int expResult = 1;
        int result = instance.size();
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class PluginManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Plugin p = new Plugin(new File("."));
        PluginManager instance = system.pluginManager;
        instance.add(p);
        instance.remove(p);
        assertEquals(0, instance.size());
    }

}