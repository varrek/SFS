/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.math.BigInteger;
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
public class ShamirTest {

    public ShamirTest() {
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
     * Test of split method, of class Shamir.
     */
    @Test
    public void testSplit() {
        System.out.println("split");
        final Shamir shamir = new Shamir(11, 20);
        final BigInteger secret = new BigInteger("1234567890123456789012345678901234567890");
        final Shamir.SecretShare[] shares = shamir.split(secret);
        final BigInteger prime = shamir.getPrime();
        final Shamir shamir2 = new Shamir(11, 20);
        BigInteger expResult = shamir2.combine(shares, prime);
        Shamir.SecretShare[] result = shamir.split(secret);
        assertEquals(secret, expResult);
    }

    /**
     * Test of combine method, of class Shamir.
     */
    @Test
    public void testCombine() {
        System.out.println("combine");
        final Shamir shamir = new Shamir(11, 20);
        final BigInteger secret = new BigInteger("1234567890123456789012345678901234567890");
        final Shamir.SecretShare[] shares = shamir.split(secret);
        final BigInteger prime = shamir.getPrime();
        final Shamir shamir2 = new Shamir(11, 20);
        BigInteger expResult = shamir2.combine(shares, prime);
        Shamir.SecretShare[] result = shamir.split(secret);
        assertEquals(secret, expResult);
    }

}
