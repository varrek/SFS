/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Varrep
 */
public class FolderTest {

    public FolderTest() {
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
     * Test of getFiles method, of class Folder.
     */
    @Test
    public void testGetFiles() {
        System.out.println("getFiles");
        Folder instance = new Folder();
        List<AbsctactFile> expResult = null;
        List<AbsctactFile> result = instance.getFiles();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFiles method, of class Folder.
     */
    @Test
    public void testSetFiles() {
        System.out.println("setFiles");
        List<AbsctactFile> files = null;
        Folder instance = new Folder();
        instance.setFiles(files);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class Folder.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        Folder instance = new Folder();
        instance.setName("testFol");
        instance.setParent_location(instance);
        String expResult = "\\testFol";
        String result = instance.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPath method, of class Folder.
     */
    @Test
    public void testGetPathInFolder() {
        System.out.println("getPath");
        Folder instance = new Folder();
        instance.setName("testFol");
        instance.setParent_location(instance);
        Folder innerInstance = new Folder();
        innerInstance.setName("testFolInner");
        innerInstance.setParent_location(instance);
        String expResult = "\\testFol\\testFolInner";
        String result = innerInstance.getPath();
        assertEquals(expResult, result);
    }

}
