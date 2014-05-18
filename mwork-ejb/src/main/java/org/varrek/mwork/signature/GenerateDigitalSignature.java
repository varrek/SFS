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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Varrep
 */
public class GenerateDigitalSignature {

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
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA", "SUN");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(1024, random);
            KeyPair pair = keyGen.generateKeyPair();
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
            byte[] key = pub.getEncoded();
            FileOutputStream keyfos = new FileOutputStream(fileToSign.getPath() + ".KEY");
            keyfos.write(key);
            keyfos.close();
            result = "trueddd" + keyfos.toString();
            System.out.println("Done Signing");

        } catch (Exception e) {
        }
        return result;
    }

    public static boolean verifySigh(File fileToSign) {
        String result = "falseed";
        boolean verifies = false;
        {
            try {
                /* Получение encoded public key из файла “pubkey” */
                byte[] encKey = readFromFile(fileToSign.getPath() + ".KEY");

                /* Создание спецификации ключа */
                X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(encKey);

                /* Создание объектов Лунафсещкн и ЗгидшсЛун*/
                KeyFactory keyFactory = KeyFactory.getInstance("DSA", "SUN");
                PublicKey pubKey = keyFactory.generatePublic(pubKeySpec);

                /* Чтение подписи из файла “signature” */
                byte[] sigToVerify = readFromFile(fileToSign.getPath() + ".SIGN");

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
