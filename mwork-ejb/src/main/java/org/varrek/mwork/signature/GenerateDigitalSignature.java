/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Varrep
 */
public class GenerateDigitalSignature {

    public static void dumpKeyPair(KeyPair keyPair) {
        PublicKey pub = keyPair.getPublic();
        System.out.println("Public Key: " + getHexString(pub.getEncoded()));

        PrivateKey priv = keyPair.getPrivate();
        System.out.println("Private Key: " + getHexString(priv.getEncoded()));
    }

    public static String getHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    public static void SaveKeyPair(String path, KeyPair keyPair) throws IOException {
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        // Store Public Key.
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(
                publicKey.getEncoded());
        FileOutputStream fos = new FileOutputStream(path + "/public.key");
        fos.write(x509EncodedKeySpec.getEncoded());
        fos.close();

        // Store Private Key.
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(
                privateKey.getEncoded());
        fos = new FileOutputStream(path + "/private.key");
        fos.write(pkcs8EncodedKeySpec.getEncoded());
        fos.close();
    }

    public static KeyPair LoadKeyPair(String path, String algorithm)
            throws IOException, NoSuchAlgorithmException,
            InvalidKeySpecException {
        // Read Public Key.
        File filePublicKey = new File(path + "/public.key");
        FileInputStream fis = new FileInputStream(path + "/public.key");
        byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
        System.out.println(encodedPublicKey);
        fis.read(encodedPublicKey);
        fis.close();

        // Read Private Key.
        File filePrivateKey = new File(path + "/private.key");
        fis = new FileInputStream(path + "/private.key");
        byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
        fis.read(encodedPrivateKey);
        fis.close();

        // Generate KeyPair.
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(
                encodedPublicKey);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(
                encodedPrivateKey);
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);

        return new KeyPair(publicKey, privateKey);
    }

    public static byte[] readFromFile(String fileName) {
        byte[] info;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            info = new byte[fis.available()];
            fis.read(info);
            fis.close();
        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
            info = new byte[0];
        }
        return (info);
    }// copyFromFile ()

    public static String generateSigh(File fileToSign) {
        String result = "falseed";
        try {
            //KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            //SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            //keyGen.initialize(1024, random);
            //KeyPair pair = keyGen.generateKeyPair();
            //PrivateKey priv = pair.getPrivate();
            // PublicKey pub = pair.getPublic();
            String path = "d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\keys\\varrek";
            KeyPair pair = GenerateDigitalSignature.LoadKeyPair(path, "DSA");
            PrivateKey priv = pair.getPrivate();
            PublicKey pub = pair.getPublic();
            Signature dsa = Signature.getInstance("SHA1withDSA", "SUN");
            dsa.initSign(priv);
            FileInputStream fis = new FileInputStream(fileToSign);
            BufferedInputStream bufin = new BufferedInputStream(fis);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = bufin.read(buffer)) >= 0) {
                dsa.update(buffer, 0, len);
            }
            bufin.close();
            byte[] realSig = dsa.sign();
            FileOutputStream sigfos = new FileOutputStream(fileToSign.getPath() + ".SIGN");
            sigfos.write(realSig);
            sigfos.close();
            // byte[] key = pub.getEncoded();
            //FileOutputStream keyfos = new FileOutputStream(fileToSign.getPath() + ".KEY");
            //keyfos.write(key);
            //keyfos.close();
            result = "trueddd";
            System.out.println("Done Signing");

        } catch (Exception e) {
        }
        return result;
    }

    public static boolean verifySigh(File fileToSign) {
        boolean verifies = false;
        {
            try {
                /* Получение encoded public key из файла “pubkey” */
               // byte[] encKey = readFromFile(fileToSign.getPath() + ".KEY");

                /* Создание спецификации ключа */
                // X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                // PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);
                String path = "d:\\Documents\\Varrek\\Programs\\magwork\\Repos\\keys\\varrek";
                KeyPair pair = GenerateDigitalSignature.LoadKeyPair(path, "DSA");
                System.out.println("Loaded Key Pair");
                dumpKeyPair(pair);
                PublicKey pubKey = pair.getPublic();

                /* Чтение подписи из файла “signature” */
                byte[] sigToVerify = readFromFile(fileToSign.getPath() + ".SIGN");
                System.out.println(fileToSign.getPath() + ".SIGN");
                /* Создание объекта класса Signature и инициализация с помощью открытого ключа */
                Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
                sig.initVerify(pubKey);

                /* Чтение данных из файла “data” и вызов метода update() */
                FileInputStream datafis = new FileInputStream(fileToSign);
                BufferedInputStream bufin = new BufferedInputStream(datafis);
                byte[] buffer = new byte[1024];
                int len;
                while (bufin.available() != 0) {
                    len = bufin.read(buffer);
                    sig.update(buffer, 0, len);
                }
                bufin.close();
                /* Верификация */
                verifies = sig.verify(sigToVerify);
                System.out.println("Signature verifies: " + verifies);
            } catch (Exception e) {
                System.err.println("Caught exception " + e.toString());
            }
        }
        return verifies;
    }
}
