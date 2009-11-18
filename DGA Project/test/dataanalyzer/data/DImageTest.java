/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataanalyzer.data;

import dataanalyzer.DGA;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
public class DImageTest {

    public DImageTest() {
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
     * Test of getFrameIcon method, of class DImage.
     */
    @Test
    public void testGetFrameIcon() {
        System.out.println("getFrameIcon");
        DGA system = null;
        Image expResult = null;
        try {
            expResult = ImageIO.read(new File("build\\classes\\images\\accessories-calculator.png"));
        } catch (IOException ex) {
            Logger.getLogger(DImageTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image result = DImage.getFrameIcon(system);
        assertEquals(expResult.getWidth(null), result.getWidth(null));
        assertEquals(expResult.getHeight(null), result.getHeight(null));
    }

}