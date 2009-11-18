/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer;

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
    dataanalyzer.entity.EntitySuite.class,
    dataanalyzer.manager.ManagerSuite.class,
    dataanalyzer.data.DataSuite.class,
    dataanalyzer.util.UtilSuite.class,
    dataanalyzer.UserInterfaceTest.class
})
public class DataanalyzerSuite {

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