/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.user;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Varrep
 */
public class UserControllerTest {

    private ArrayList<Integer> addedUsers;
    private Integer existingUserId;

    public UserControllerTest() {
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
     * Test of getMD5 method, of class UserController.
     */
    @Test
    public void testGetMD5() {
        System.out.println("getMD5");
        String str = "";
        String expResult = "";
        String result = UserController.getMD5(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByID method, of class UserController.
     */
    @Test
    public void testGetUserByID() {
        System.out.println("getUserByID");
        User instance = UserController.getUserByID(8);
        assertEquals(instance.getId(), 8);
    }

    /**
     * Test of register method, of class UserController.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testRegister() throws Exception {
        System.out.println("register");
        RegistrationResult result;
        UserController instance = new UserController();
        result = instance.register("testRegisterUser", "testpassword", "testpassword",
                "test@mail.ru", "test@mail.ru", "fullname", "address");
        assertEquals(RegistrationResult.RegistrationSuccessfull, result);
        System.out.println("Register user with existing username.");
        result = instance.register("testRegisterUser", "testpassword", "testpassword",
                "test@mail.ru", "test@mail.ru", "fullname", "address");
        assertEquals(RegistrationResult.UsernameExists, result);
        System.out.println("Register user with password mismatch.");
        result = instance.register("testRegisterUser2", "testpassword1", "testpassword2",
                "test@mail.ru", "test@mail.ru", "fullname", "address");
        assertEquals(RegistrationResult.PasswordsDoesntMatch, result);
        System.out.println("Register user with email mismatch.");
        result = instance.register("testRegisterUser3", "testpassword", "testpassword",
                "test1@mail.ru", "test2@mail.ru", "fullname", "address");
        assertEquals(RegistrationResult.EmailsDoesntMatch, result);
    }

    /**
     * Test of login method, of class UserController.
     */
    @Test
    public void testLogin() {
        UserController instance = new UserController();
        System.out.println("Log in with correct info.");
        Boolean result = instance.login("testRegisterUser", "testpassword", "sessionid");
        assertEquals(Boolean.TRUE, result);
        System.out.println("Log in with incorrect info.");
        result = instance.login("testRegisterUserIncorrect", "testpassword", "sessionid");
        assertEquals(Boolean.FALSE, result);
        System.out.println("Log in with incorrect info.");
        result = instance.login("testRegisterUser", "testpassword123", "sessionid");
        assertEquals(Boolean.FALSE, result);
    }

    /**
     * Test of logout method, of class UserController.
     */
    @Test
    public void testLogout() {
        System.out.println("logout");
        User user = null;
        UserController instance = new UserController();
        User cuser = instance.getCurrentUser("sessionid");
        Boolean expectedResult = true;
        Boolean result = instance.logout(cuser);
        assertEquals(expectedResult, result);

    }

    /**
     * Test of getCurrentUser method, of class UserController.
     */
    @Test
    public void testGetCurrentUser() {
        System.out.println("getCurrentUser");
        UserController instance = new UserController();
        System.out.println("Retrieve current user by it's session id.");
        User cUser = instance.getCurrentUser("sessionid");
        assertEquals("testRegisterUser", cUser.getLogin());
        System.out.println("Retrieve current user by wrong session id.");
        cUser = instance.getCurrentUser("wrongsessionid");
        assertEquals(null, cUser);
    }

}
