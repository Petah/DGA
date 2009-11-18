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
public class UpdateTest {

    public UpdateTest() {
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
     * Test of compareTo method, of class Update.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Update o = new Update();
        Update instance = new Update();
        o.name = "file.ext";
        instance.name = "file.ext";
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

}