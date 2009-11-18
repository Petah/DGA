/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer;

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
public class UserInterfaceTest {

    public UserInterfaceTest() {
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
     * Test of getVerbosity method, of class UserInterface.
     */
    @Test
    public void testGetVerbosity() {
        System.out.println("getVerbosity");
        UserInterface instance = new UserInterfaceImpl();
        instance.setVerbosity(0);
        int expResult = 0;
        int result = instance.getVerbosity();
        assertEquals(expResult, result);
    }

    /**
     * Test of setVerbosity method, of class UserInterface.
     */
    @Test
    public void testSetVerbosity() {
        System.out.println("setVerbosity");
        int verbosity = 0;
        UserInterface instance = new UserInterfaceImpl();
        instance.setVerbosity(verbosity);
        assertEquals(instance.getVerbosity(), verbosity);
    }

    public class UserInterfaceImpl extends UserInterface {

        public void collectOutput(SystemListener systemListener) {
        }

        public void handleError(String s) {
        }

        public void handleException(Throwable ex) {
        }

        public void out(Object o) {
        }

        public void sendOutput(Output o) {
        }

    }

}