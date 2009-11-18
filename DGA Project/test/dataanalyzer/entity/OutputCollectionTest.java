/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.entity;

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
public class OutputCollectionTest {

    public OutputCollectionTest() {
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
     * Test of add method, of class OutputCollection.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Output o = new Output(this, this);
        OutputCollection instance = new OutputCollection();
        instance.add(o);
        assertEquals(instance.getAll().get(0).getOwner(), this);
        assertEquals(instance.getAll().get(0).getOutput(), this);
    }

    /**
     * Test of getAll method, of class OutputCollection.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        Output o = new Output(this, this);
        OutputCollection instance = new OutputCollection();
        instance.add(o);
        ArrayList expResult = new ArrayList();
        expResult.add(o);
        ArrayList result = instance.getAll();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class OutputCollection.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        OutputCollection instance = new OutputCollection();
        assertTrue(instance.toString() instanceof String);
    }

}