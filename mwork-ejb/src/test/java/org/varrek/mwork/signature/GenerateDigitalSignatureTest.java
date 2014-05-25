/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.io.File;
import static java.lang.Math.random;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Varrep
 */
public class GenerateDigitalSignatureTest {

    public GenerateDigitalSignatureTest() {
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
     * Test of dumpKeyPair method, of class GenerateDigitalSignature.
     */
    @Test
    public void testDumpKeyPair() {
        System.out.println("dumpKeyPair");
        KeyPair keyPair = null;
        GenerateDigitalSignature.dumpKeyPair(keyPair);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of SaveKeyPair method, of class GenerateDigitalSignature.
     */
    @Test
    public void testSaveKeyPair() throws Exception {
       /* System.out.println("SaveKeyPair");
        String path = "d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\keys\\varrek";
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
        keyGen.initialize(1024, random);
        KeyPair generatedKeyPair = keyGen.genKeyPair();

        System.out.println("Generated Key Pair");
        GenerateDigitalSignature.dumpKeyPair(generatedKeyPair);
        GenerateDigitalSignature.SaveKeyPair(path, generatedKeyPair);
        assertEquals(true, true);*/
    }

    /**
     * Test of LoadKeyPair method, of class GenerateDigitalSignature.
     */
    @Test
    public void testLoadKeyPair() throws Exception {
        System.out.println("LoadKeyPair");
        String path = "d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\keys\\varrek";
        String algorithm = "DSA";
        KeyPair result = GenerateDigitalSignature.LoadKeyPair(path, algorithm);
        PublicKey pub = result.getPublic();
        System.out.println("Public Key: " + GenerateDigitalSignature.getHexString(pub.getEncoded()));
        PrivateKey priv = result.getPrivate();
        System.out.println("Private Key: " + GenerateDigitalSignature.getHexString(priv.getEncoded()));
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(true, true);
    }

    /**
     * Test of readFromFile method, of class GenerateDigitalSignature.
     */
    @Test
    public void testReadFromFile() {
        System.out.println("readFromFile");
        String fileName = "";
        byte[] expResult = null;
        byte[] result = GenerateDigitalSignature.readFromFile(fileName);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateSigh method, of class GenerateDigitalSignature.
     */
    @Test
    public void testGenerateSigh() {
        System.out.println("generateSigh");
        File fileToSign = new File("d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\test\\AC_calc_options_20130927.txt");
        String expResult = "trueddd";
        String result = GenerateDigitalSignature.generateSigh(fileToSign);
        assertEquals(expResult, result);
    }

    /**
     * Test of verifySigh method, of class GenerateDigitalSignature.
     */
    @Test
    public void testVerifySigh() {
        System.out.println("verifySigh");
        File fileToSign = new File("d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\test\\dir.php");
        boolean expResult = true;
        boolean result = GenerateDigitalSignature.verifySigh(fileToSign);
        assertEquals(expResult, result);
    }

}
