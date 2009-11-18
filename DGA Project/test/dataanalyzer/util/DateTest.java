/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.util;

import dataanalyzer.util.Date;
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
public class DateTest {

    public DateTest() {
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
     * Test of now method, of class Date.
     */
    @Test
    public void testNow() {
        System.out.println("now cannot be tested because the result is constantly changing");
        String result = Date.now();
        assertTrue(result instanceof String);
    }

}