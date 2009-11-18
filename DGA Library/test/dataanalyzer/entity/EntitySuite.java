/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.entity;

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
    dataanalyzer.entity.DataDoubleTest.class,
    dataanalyzer.entity.SequenceDataTest.class,
    dataanalyzer.entity.DataIntTest.class,
    dataanalyzer.entity.DataByteTest.class,
    dataanalyzer.entity.AlgorithmTest.class,
    dataanalyzer.entity.OutputTest.class,
    dataanalyzer.entity.AlgorithmProcessTest.class,
    dataanalyzer.entity.DataLongTest.class
})
public class EntitySuite {

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
