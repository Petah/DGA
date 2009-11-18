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
public class FormatTest {

    public FormatTest() {
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
     * Test of round method, of class Format.
     */
    @Test
    public void testRound() {
        System.out.println("round");
        float Rval = 11.111111F;
        int Rpl = 3;
        float expResult = 11.111F;
        float result = Format.round(Rval, Rpl);
        assertTrue(expResult == result);
    }

    /**
     * Test of bytes method, of class Format.
     */
    @Test
    public void testBytes() {
        System.out.println("bytes");
        assertEquals("1.0Kb", Format.bytes(1024));
        assertEquals("450b", Format.bytes(450));
        assertEquals("9.0Mb", Format.bytes(10000000));
    }

}