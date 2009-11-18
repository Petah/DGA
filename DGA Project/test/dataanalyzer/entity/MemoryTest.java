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
public class MemoryTest {

    public MemoryTest() {
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
     * Test of getFree method, of class Memory.
     */
    @Test
    public void testGetFree() {
        System.out.println("getFree");
        Memory instance = new Memory();
        String result = instance.getFree();
        assertTrue(result.length() > 1);
    }

    /**
     * Test of getUsed method, of class Memory.
     */
    @Test
    public void testGetUsed() {
        System.out.println("getUsed");
        Memory instance = new Memory();
        String result = instance.getUsed();
        assertTrue(result.length() > 1);
    }

    /**
     * Test of getAllocated method, of class Memory.
     */
    @Test
    public void testGetAllocated() {
        System.out.println("getAllocated");
        Memory instance = new Memory();
        String result = instance.getAllocated();
        assertTrue(result.length() > 1);
    }

    /**
     * Test of getMaximum method, of class Memory.
     */
    @Test
    public void testGetMaximum() {
        System.out.println("getMaximum");
        Memory instance = new Memory();
        String result = instance.getMaximum();
        assertTrue(result.length() > 1);
    }

    /**
     * Test of update method, of class Memory.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Memory instance = new Memory();
        instance.update();
    }

}