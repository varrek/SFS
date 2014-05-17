/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Properties;
import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.repo.Repo;
import org.varrek.mwork.files.AbsctactFile;

/**
 *
 * @author Varrep
 */
public class FileController {

    public boolean createDirectory(Repo rep, Folder parent, final String dirName) throws IOException {
        boolean result = true;
        try {
            Properties properties = new Properties();
            try {//InputStream is = this.getClass().getResourceAsStream("/META_INF/config.properties"); 
                InputStream is = this.getClass().getResourceAsStream("/META-INF/config.properties");
                properties.load(is);
            } catch (IOException e) {
                throw e;
            }
            String repoRoot = properties.getProperty("contex.repoRoot");
            boolean exists = false;
            for (AbsctactFile currentFile : parent.getFiles()) {
                if (currentFile.getName().equals(dirName)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Path testDirectoryPath = Paths.get(repoRoot + '\\' + parent.getPath() + '\\' + dirName);
                Path testDirectory = Files.createDirectory(testDirectoryPath);
                Session sess = HibernateUtil.openSession();
                Folder newFolder = new Folder();
                newFolder.setDate_created(new java.sql.Date(new java.util.Date().getTime()));
                newFolder.setDate_modyfied(new java.sql.Date(new java.util.Date().getTime()));
                newFolder.setName(dirName);
                newFolder.setParent_location(parent);
                ArrayList<AbsctactFile> list = new ArrayList<>();
                list.add(new File());
                newFolder.setFiles(list);
                try {
                    sess.beginTransaction();
                    sess.saveOrUpdate(newFolder);
                    sess.getTransaction().commit();
                } catch (Exception e) {
                    throw e;
                }
                System.out.println("Directory created successfully!");
            } else {
                result = false;
            }
        } catch (IOException ex) {
            throw ex;
        }
        return result;
    }

    public boolean copyFile(final File sourceFileName, final Folder targetFolderName) {
        /* Path newFile = FileSystems.getDefault().
         getPath(targetFileName);
         Path copiedFile = FileSystems.getDefault().
         getPath(sourceFileName);
         try {

         Files.createFile(newFile);
         System.out.println("File created successfully!");
         Files.copy(newFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
         System.out.println("File copied successfully!");
         } catch (IOException e) {

         System.out.println("IO Exception.");
         }*/
        return true;
    }

    public boolean copyDir(final String sourceDirName, final String targetDirName) {
        Path originalDirectory = FileSystems.getDefault().
                getPath(sourceDirName);

        Path newDirectory = FileSystems.getDefault().
                getPath(targetDirName);

        try {

            Files.copy(originalDirectory, newDirectory);

            System.out.println("Directory copied successfully!");
        } catch (IOException e) {

            e.printStackTrace();
        }

        return true;
    }

}
