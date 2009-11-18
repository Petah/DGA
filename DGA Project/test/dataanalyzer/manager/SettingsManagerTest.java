/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Setting;
import dataanalyzer.listeners.SystemListener;
import java.util.HashMap;
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
public class SettingsManagerTest {

    private static DGA system;
    private static Setting setting;
    public SettingsManagerTest() {
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
        setting = new Setting("test setting", 0, "Integer");
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of add method, of class SettingsManager.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Setting s = setting;
        SettingsManager instance = system.settingsManager;
        instance.add(s);
        assertEquals(s, instance.find("test setting"));
    }

    /**
     * Test of get method, of class SettingsManager.
     */
    @Test
    public void testGet_int() {
        System.out.println("get cannot test method because it is an unused implementation");
    }

    /**
     * Test of size method, of class SettingsManager.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Setting s = setting;
        SettingsManager instance = system.settingsManager;
        int expResult = instance.size();
        instance.add(s);
        int result = instance.size();
        assertEquals(expResult + 1, result);
    }

    /**
     * Test of findValue method, of class SettingsManager.
     */
    @Test
    public void testFindValue() {
        System.out.println("findValue");
        String s = "test setting";
        SettingsManager instance = system.settingsManager;
        instance.add(setting);
        Object expResult = 0;
        Object result = instance.findValue(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of remove method, of class SettingsManager.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        SettingsManager instance = system.settingsManager;
        int initSize = instance.size();
        instance.add(setting);
        assertEquals(instance.size(), initSize + 1);
        instance.remove(setting);
        assertEquals(instance.size(), initSize);
    }

    /**
     * Test of find method, of class SettingsManager.
     */
    @Test
    public void testFind_String() {
        System.out.println("find");
        String s = "test setting";
        SettingsManager instance = system.settingsManager;
        instance.add(setting);
        Setting expResult = setting;
        Setting result = instance.find(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of get method, of class SettingsManager.
     */
    @Test
    public void testGet_0args() {
        System.out.println("get simple getting method does not require testing");
    }

}