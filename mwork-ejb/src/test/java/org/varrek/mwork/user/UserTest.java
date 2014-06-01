/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.hibernate.HibernateException;
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
import org.varrek.mwork.repo.RepoAccess;

/**
 *
 * @author Varrep
 */
public class UserTest {

    private User testOutput;
    private User emptyHumanUser;
    private ArrayList<Integer> addedUsers;
    private static final Logger LOGGER = Logger.getLogger("JSP");

    public static Logger getLogger() {
        return LOGGER;
    }

    public UserTest() {
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
    /*
     @Test
     public void createUser() throws ParseException {

     User instance = new User();
     // Human fields
     instance.setFullName("Andrew Low");
     instance.setAddress("Peter STreet");
     // User-Specific fields
     instance.setLogin("admin");
     instance.setEmail("test@mail.ru");
     instance.setPass("12345");
     instance.setDescription("test...test...");
     testOutput = instance;
     }

     @Test
     public void persistUser() {
     User instance = new User();
     // Human fields
     instance.setFullName("Andrew Low");
     instance.setAddress("Peter STreet");
     // User-Specific fields
     instance.setLogin("admin2222");
     instance.setEmail("test@mail.ru");
     instance.setPass("12345");
     instance.setDescription("test...test...");
     Session sess = HibernateUtil.openSession();
     try {
     sess.beginTransaction();
     sess.saveOrUpdate(instance);
     sess.getTransaction().commit();
     } catch (HibernateException ex) {
     System.out.println("Error trying to save human to DB!");
     fail("Error trying save User to DB!" + ex.getMessage());
     }
     }

     @Test
     public void retrieveUser() {
     System.out.println("Trying to retrieve persisted User from DB.");
     Integer id = 5;
     User instance = null;
     Session sess = HibernateUtil.openSession();
     try {
     instance = (User) sess.get(User.class, id);
     } catch (HibernateException ex) {
     System.out.println("Error trying to retrieve User from DB!");
     fail("Error trying retrieve human from DB!" + ex.getMessage());
     }
     }
     */

    @Test
    public void testGetRepos() {
        System.out.println("Trying to retrieve persisted User from DB.");
        Integer id = 1;
        User instance = null;
        Session sess = HibernateUtil.openSession();
        try {
            instance = (User) sess.get(User.class, id);
            getLogger().log(Priority.INFO, instance.getRepositories());
            System.out.println(instance.getRepos().size());
            instance.getRepos();
            for (Repo curr: instance.getRepos()) {
                 System.out.println(curr.getName());
            }
        } catch (HibernateException ex) {
            System.out.println("Error trying to retrieve User from DB!");
            fail("Error trying retrieve human from DB!" + ex.getMessage());
        }
    }

}
