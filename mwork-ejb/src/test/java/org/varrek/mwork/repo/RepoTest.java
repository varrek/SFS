/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class RepoTest {

    public RepoTest() {
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
     * Test of getRepModer method, of class Repo.
     */
    @Test
    public void testGetRepModer() {
        System.out.println("getRepModer");
        Integer id = 9;
        Repo instance = null;
        Session sess = HibernateUtil.openSession();
        try {
            instance = (Repo) sess.get(Repo.class, id);
        } catch (HibernateException ex) {
            System.out.println("Error trying to retrieve User from DB!");
        }
        List<User> expResult = null;
        List<User> result = instance.getRepModer();
         for (User curr : result) {
                System.out.println(curr.getLogin());
        }
        assertEquals(expResult, result);
    }

}
