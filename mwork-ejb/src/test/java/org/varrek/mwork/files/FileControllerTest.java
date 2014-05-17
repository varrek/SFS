/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.varrek.mwork.files;

import java.io.IOException;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.varrek.mwork.HibernateUtil;
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
     * @throws java.io.IOException
     */
    @Test
    public void testCreateDirectory() throws IOException {
        System.out.println("createDirectory");
         Session sess = HibernateUtil.openSession();
        Repo rep = new Repo ("test","",new ArrayList <>(),new Keys());
        Folder parent = rep.getRoot();
        parent.setId(2);
        Folder temp=(Folder)sess.get(AbsctactFile.class,1);
        parent.setParent_location(temp);
        parent.setFiles(null);

    
        try {
                    sess.beginTransaction();
                    sess.save(parent);
                    sess.getTransaction().commit();
                } catch (Exception e) {
                    throw e;
                }
        String dirName = "testDir";
        FileController instance = new FileController();
        boolean expResult = true;
        boolean result = instance.createDirectory(rep, parent, dirName);
        assertEquals(expResult, result);
    }

    /**
     * Test of copyFile method, of class FileController.
     */
    @Test
    public void testCopyFile() {
        System.out.println("copyFile");
        File sourceFileName = null;
        Folder targetFolderName = null;
        FileController instance = new FileController();
        boolean expResult = false;
        boolean result = instance.copyFile(sourceFileName, targetFolderName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyDir method, of class FileController.
     */
    @Test
    public void testCopyDir() {
        System.out.println("copyDir");
        String sourceDirName = "";
        String targetDirName = "";
        FileController instance = new FileController();
        boolean expResult = false;
        boolean result = instance.copyDir(sourceDirName, targetDirName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
