/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.gui;

import dataanalyzer.interfaces.ISystem;
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
public class IAlgorithmPanelTest {

    public IAlgorithmPanelTest() {
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
     * Test of collectInput method, of class IAlgorithmPanel.
     */
    @Test
    public void testCollectInput() {
        System.out.println("collectInput");
        IAlgorithmPanel instance = new IAlgorithmPanelImpl();
        instance.collectInput();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystem method, of class IAlgorithmPanel.
     */
    @Test
    public void testSetSystem() {
        System.out.println("setSystem");
        ISystem system = null;
        IAlgorithmPanel instance = new IAlgorithmPanelImpl();
        instance.setSystem(system);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IAlgorithmPanelImpl extends IAlgorithmPanel {

        public void collectInput() {
        }
    }

}