/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Update;
import dataanalyzer.listeners.CompletionListener;
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
public class UpdateManagerTest {

    private static DGA system;
    private static Update update;
    public UpdateManagerTest() {
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
        update = new Update();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of check method, of class UpdateManager.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        CompletionListener c = null;
        UpdateManager instance = system.updateManager;
        instance.check(c);
        while (instance.isChecking());
        assertTrue(system.updateManager.size() > 0);
    }


    /**
     * Test of get method, of class UpdateManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get");
        UpdateManager instance = system.updateManager;
        instance.add(update);
        ArrayList expResult = new ArrayList();
        expResult.add(update);
        ArrayList result = instance.get();
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class UpdateManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get");
        int i = 0;
        UpdateManager instance = system.updateManager;
        instance.add(update);
        Update expResult = update;
        Update result = instance.get(i);
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class UpdateManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        UpdateManager instance = system.updateManager;
        instance.add(update);
        assertTrue(system.updateManager.size() == 1);
    }

    /**
     * Test of remove method, of class UpdateManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        UpdateManager instance = system.updateManager;
        instance.add(update);
        instance.remove(update);
        assertTrue(system.updateManager.size() == 0);
    }

    /**
     * Test of size method, of class UpdateManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        UpdateManager instance = system.updateManager;
        instance.add(update);
        assertTrue(system.updateManager.size() == 1);
    }

    /**
     * Test of getVersion method, of class UpdateManager.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        UpdateManager instance = system.updateManager;
        instance.setVersion("test");
        String expResult = "test";
        String result = instance.getVersion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVersion method, of class UpdateManager.
     */
    @Test
    public void testSetVersion() {
        System.out.println("setVersion");
        String version = "test";
        UpdateManager instance = system.updateManager;
        instance.setVersion(version);
        assertEquals("test", instance.getVersion());
    }

}