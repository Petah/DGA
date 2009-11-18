/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.data;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.listeners.SystemListener;
import dataanalyzer.manager.SettingsManager;
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
public class DDefaultSettingsTest {

    private static DGA system;
    public DDefaultSettingsTest() {
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
     * Test of setDefaults method, of class DDefaultSettings.
     */
    @Test
    public void testSetDefaults() {
        System.out.println("setDefaults");
        SettingsManager settingsManager = system.settingsManager;
        DDefaultSettings.setDefaults(settingsManager);
        assertTrue(system.settingsManager.get().size() > 0);
    }

}