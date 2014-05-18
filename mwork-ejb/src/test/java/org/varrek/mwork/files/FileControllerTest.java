/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.varrek.mwork.repo.Keys;
import org.varrek.mwork.repo.Repo;

/**
 *
 * @author Varrep
 */
public class FileControllerTest {

    public FileControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createDirectory method, of class FileController.
     */
    @Test
    public void testCreateDirectory() throws Exception {
        try {
            Properties properties = new Properties();
            try {
                InputStream is = this.getClass().getResourceAsStream("/META-INF/config.properties");
                properties.load(is);
            } catch (IOException e) {
                throw e;
            }

            String repoRoot = properties.getProperty("contex.repoRoot");
            System.out.println("createDirectory");
            Repo rep = new Repo("test", "", new ArrayList<>(), new Keys());
            System.out.println(repoRoot + "\\" + rep.getName());
            File parent = new File(repoRoot + "\\" + rep.getName());
            String dirName = "inTest";
            FileController instance = new FileController();
            boolean expResult = true;
            boolean result = instance.createDirectory(rep, parent, dirName);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            throw ex;
        }
    }

    /**
     * Test of recursiveCopy method, of class FileController.
     */
    
    @Test
    public void testRecursiveCopy() throws Exception {
       try {
            Properties properties = new Properties();
            try {
                InputStream is = this.getClass().getResourceAsStream("/META-INF/config.properties");
                properties.load(is);
            } catch (IOException e) {
                throw e;
            }

            String repoRoot = properties.getProperty("contex.repoRoot");
            System.out.println("copyDirectory");
            Repo rep = new Repo("test", "", new ArrayList<>(), new Keys());
            System.out.println(repoRoot + "\\" + rep.getName());
            File parent = new File(repoRoot + "\\" + rep.getName() + "\\inTest");
            File dirName = new File(repoRoot + "\\" + rep.getName() + "\\testCopy\\");
            FileController instance = new FileController();
            boolean expResult = true;
            boolean result = instance.recursiveCopy(parent, dirName);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            throw ex;
        }
    }
    /**
     * Test of recursiveCopy method, of class FileController.
     */
    
    @Test
    public void testRecursiveCopyFile() throws Exception {
        try {
            Properties properties = new Properties();
            try {
                InputStream is = this.getClass().getResourceAsStream("/META-INF/config.properties");
                properties.load(is);
            } catch (IOException e) {
                throw e;
            }

            String repoRoot = properties.getProperty("contex.repoRoot");
            System.out.println("copyDirectory");
            Repo rep = new Repo("test", "", new ArrayList<>(), new Keys());
//            System.out.println(repoRoot + "\\" + rep.getName());
            File parent = new File(repoRoot + "\\" + rep.getName() + "\\inTest\\");
            File dirName = new File(repoRoot + "\\" + rep.getName() + "\\inTest2\\");
          //  File parent = new File(repoRoot + "\\" + rep.getName() + "\\inTest\\AndhorUser.java");
           // File dirName = new File(repoRoot + "\\" + rep.getName() + "\\inTest\\inTest\\AndhorUser.java");
//            System.out.println(repoRoot + "\\" + rep.getName() + "\\inTest\\AndhorUser.java");
//            System.out.println(repoRoot + "\\" + rep.getName() + "\\inTest\\inTest\\"+ parent.getName());
            FileController instance = new FileController();
            boolean expResult = true;
            boolean result = instance.recursiveCopy(parent, dirName);
            assertEquals(expResult, result);
        } catch (IOException ex) {
            throw ex;
        }
    }
    

}
