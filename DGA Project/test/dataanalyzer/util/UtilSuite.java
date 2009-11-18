/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author davnei06
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    dataanalyzer.util.FormatTest.class,
    dataanalyzer.util.MD5Test.class,
    dataanalyzer.util.DateTest.class,
    dataanalyzer.util.SuppressOutputTest.class,
    dataanalyzer.util.LookAndFeelSetterTest.class,
    dataanalyzer.util.UniqueTest.class,
    dataanalyzer.util.VersionTest.class
})
public class UtilSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
}