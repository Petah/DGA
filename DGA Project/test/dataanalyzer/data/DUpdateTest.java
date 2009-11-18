/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataanalyzer.data;

import dataanalyzer.DGA;
import dataanalyzer.UserInterface;
import dataanalyzer.entity.Output;
import dataanalyzer.entity.Update;
import dataanalyzer.listeners.CompletionListener;
import dataanalyzer.listeners.SystemListener;
import javax.swing.JOptionPane;
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
public class DUpdateTest {

    private static DGA system;
    private static boolean complete = false;
    private static DUpdate dUpdate;

    public DUpdateTest() {
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
        dUpdate = new DUpdate(system);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of downloadAll method, of class DUpdate.
     */
    @Test
    public void testDownloadAll() {
        System.out.println("downloadAll");
        DUpdate instance = dUpdate;
        instance.addCompletionListener(new CompletionListener() {

            public void complete(Object o) {
                complete = true;
            }
        });
        instance.check();
        while (!complete);
        assertTrue(system.updateManager.get().size() > 0);
    }
}
