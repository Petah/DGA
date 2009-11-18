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
public class VersionTest {

    public VersionTest() {
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
     * Test of getVersion method, of class Version.
     */
    @Test
    public void testGetVersion() {
        System.out.println("getVersion");
        assertTrue(Version.getVersion() instanceof String);
    }

}