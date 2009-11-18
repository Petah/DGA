/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.util;

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
public class UniqueTest {

    public UniqueTest() {
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
     * Test of getLong method, of class Unique.
     */
    @Test
    public void testGetLong() {
        System.out.println("getLong");
        assertEquals(0l, Unique.getLong());
        assertEquals(1l, Unique.getLong());
        assertEquals(2l, Unique.getLong());
        assertEquals(3l, Unique.getLong());
    }

}