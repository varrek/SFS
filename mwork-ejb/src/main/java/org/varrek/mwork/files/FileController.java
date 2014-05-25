/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import org.varrek.mwork.repo.Repo;

/**
 *
 * @author Varrep
 */
public class FileController {

    public boolean createDirectory(Repo rep, File parent, final String dirName) throws IOException {
        boolean result = false;
        try {
            Properties properties = new Properties();
            try {
                InputStream is = this.getClass().getResourceAsStream("/META-INF/config.properties");
                properties.load(is);
            } catch (IOException e) {
                throw e;
            }
            String repoRoot = properties.getProperty("contex.repoRoot");
            String path = parent.getPath();
            System.out.println(path);
            File theDir = new File(path + '\\' + dirName);
            System.out.println(theDir);

            // if the directory does not exist, create it
            if (!theDir.exists()) {

                result = theDir.mkdir();

                if (result) {
                    System.out.println("DIR created");
                }
            }
        } catch (IOException ex) {
            throw ex;
        }
        return result;
    }

    public boolean recursiveCopy(File fSource, File fDest) throws Exception {
        boolean result = false;
        try {
            if (fSource.isDirectory()) {
                // A simple validation, if the destination is not exist then create it
                if (!fDest.exists()) {
                    fDest.mkdirs();
                }

                // Create list of files and directories on the current source
                // Note: with the recursion 'fSource' changed accordingly
                String[] fList = fSource.list();

                for (int index = 0; index < fList.length; index++) {
                    File dest = new File(fDest, fList[index]);
                    File source = new File(fSource, fList[index]);

                    // Recursion call take place here
                    recursiveCopy(source, dest);
                }
                result = true;
            } else {
                InputStream in = new FileInputStream(fSource);
                OutputStream out = new FileOutputStream(fDest);

                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }
}
