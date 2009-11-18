/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.data;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Plugin;
import dataanalyzer.listeners.SystemListener;
import java.io.File;
import java.io.FilenameFilter;
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
public class DPluginTest {

    private static DGA system;

    public DPluginTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        system = new DGA(new UserInterface() {

            public void handleException(Throwable ex) {
            }

            public void handleError(String s) {
            }

            public void out(Object o) {
            }

            public void collectOutput(SystemListener systemListener) {
            }

            public void sendOutput(Output o) {
            }
        });
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of load method, of class DPlugin.
     */
    @Test
    public void testLoad_String_DGA() {
        System.out.println("load");
        String fileName = "plugins\\Basic_Algorithms.jar";
        Plugin result = DPlugin.load(fileName, system);
        assertEquals(system.pluginManager.get().size(), 1);
    }

    /**
     * Test of load method, of class DPlugin.
     */
    @Test
    public void testLoad_File_DGA() {
        System.out.println("load");
        File file = new File("plugins\\Basic_Algorithms.jar");
        Plugin result = DPlugin.load(file, system);
        assertEquals(system.pluginManager.get().size(), 1);
    }

    /**
     * Test of loadAll method, of class DPlugin.
     */
    @Test
    public void testLoadAll() {
        System.out.println("loadAll");
        DPlugin.loadAll(system);
        assertEquals(system.pluginManager.get().size(), new File("plugins\\").list(new FilenameFilter() {

            public boolean accept(File dir, String name) {
                return name.endsWith(".jar") || name.endsWith(".zip");
            }
        }).length);
    }
}