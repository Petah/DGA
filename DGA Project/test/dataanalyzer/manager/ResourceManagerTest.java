/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.manager;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Memory;
import dataanalyzer.entity.Output;
import dataanalyzer.listeners.SystemListener;
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
public class ResourceManagerTest {

    private static DGA system;
    public ResourceManagerTest() {
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
     * Test of getMemory method, of class ResourceManager.
     */
    @Test
    public void testGetMemory() {
        System.out.println("getMemory");
        ResourceManager instance = system.resourceManager;
        Memory result = instance.getMemory();
        assertTrue(result.getAllocated().length() > 1);
    }

}