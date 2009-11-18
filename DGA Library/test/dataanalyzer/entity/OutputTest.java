/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

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
public class OutputTest {

    public OutputTest() {
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
     * Test of toString method, of class Output.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Output instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInformation method, of class Output.
     */
    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        Output instance = null;
        String expResult = "";
        String result = instance.getInformation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwner method, of class Output.
     */
    @Test
    public void testGetOwner() {
        System.out.println("getOwner");
        Output instance = null;
        Object expResult = null;
        Object result = instance.getOwner();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOwner method, of class Output.
     */
    @Test
    public void testSetOwner() {
        System.out.println("setOwner");
        Object owner = null;
        Output instance = null;
        instance.setOwner(owner);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class Output.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        Output instance = null;
        Object expResult = null;
        Object result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutput method, of class Output.
     */
    @Test
    public void testSetOutput() {
        System.out.println("setOutput");
        Object output = null;
        Output instance = null;
        instance.setOutput(output);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}