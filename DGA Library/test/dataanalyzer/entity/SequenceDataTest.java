/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

import java.util.ArrayList;
import java.util.Iterator;
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
public class SequenceDataTest {

    public SequenceDataTest() {
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
     * Test of addMulti method, of class SequenceData.
     */
    @Test
    public void testAddMulti() {
        System.out.println("addMulti");
        ArrayList<Data> m = null;
        SequenceData instance = new SequenceData();
        instance.addMulti(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSingle method, of class SequenceData.
     */
    @Test
    public void testAddSingle() {
        System.out.println("addSingle");
        Data s = null;
        SequenceData instance = new SequenceData();
        instance.addSingle(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class SequenceData.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        SequenceData instance = new SequenceData();
        ArrayList expResult = null;
        ArrayList result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIterator method, of class SequenceData.
     */
    @Test
    public void testGetIterator() {
        System.out.println("getIterator");
        SequenceData instance = new SequenceData();
        Iterator expResult = null;
        Iterator result = instance.getIterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMulti method, of class SequenceData.
     */
    @Test
    public void testGetMulti() {
        System.out.println("getMulti");
        int start = 0;
        int length = 0;
        SequenceData instance = new SequenceData();
        ArrayList expResult = null;
        ArrayList result = instance.getMulti(start, length);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSingle method, of class SequenceData.
     */
    @Test
    public void testGetSingle() {
        System.out.println("getSingle");
        int i = 0;
        SequenceData instance = new SequenceData();
        Data expResult = null;
        Data result = instance.getSingle(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class SequenceData.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int i = 0;
        SequenceData instance = new SequenceData();
        instance.remove(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class SequenceData.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        SequenceData instance = new SequenceData();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInformation method, of class SequenceData.
     */
    @Test
    public void testGetInformation() {
        System.out.println("getInformation");
        SequenceData instance = new SequenceData();
        String expResult = "";
        String result = instance.getInformation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SequenceData.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SequenceData instance = new SequenceData();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}