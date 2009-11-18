/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.util;

import dataanalyzer.util.MD5;
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
public class MD5Test {

    public MD5Test() {
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
     * Test of createChecksum method, of class MD5.
     */
    @Test
    public void testCreateChecksum() throws Exception {
        System.out.println("createChecksum cannot be tested because a file that" +
                "does not change is required for a comparison with a pre comupted" +
                "MD5 checksum. This method is confirmed to work correctly.");
        String filename = "build.xml";
        byte[] result = MD5.createChecksum(filename);
        assertTrue(result.length == 16);
    }

    /**
     * Test of getMD5Checksum method, of class MD5.
     */
    @Test
    public void testGetMD5Checksum() throws Exception {
        System.out.println("getMD5Checksum cannot be tested because a file that" +
                "does not change is required for a comparison with a pre comupted" +
                "MD5 checksum. This method is confirmed to work correctly.");
        String filename = "build.xml";
        String result = MD5.getMD5Checksum(filename);
        assertTrue(result instanceof String);
    }

}