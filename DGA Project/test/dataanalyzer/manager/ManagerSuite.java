/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.manager;

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
    dataanalyzer.manager.SettingsManagerTest.class,
    dataanalyzer.manager.GUIManagerTest.class,
    dataanalyzer.manager.ResourceManagerTest.class,
    dataanalyzer.manager.ProcessManagerTest.class,
    dataanalyzer.manager.AlgorithmManagerTest.class,
    dataanalyzer.manager.PluginManagerTest.class,
    dataanalyzer.manager.UpdateManagerTest.class,
    dataanalyzer.manager.OutputManagerTest.class
})
public class ManagerSuite {

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
