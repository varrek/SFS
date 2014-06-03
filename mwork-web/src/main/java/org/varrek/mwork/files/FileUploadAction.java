/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ServletContextAware;
import org.varrek.mwork.files.FileUtil;
import org.varrek.mwork.signature.GenerateDigitalSignature;

/**
 *
 * @author Varrep
 */
public class FileUploadAction extends ActionSupport implements ServletContextAware {

    private static final long serialVersionUID = -4748500436762141236L;

    private File[] file;
    private String[] fileContentType;
    private String[] fileFileName;
    private String filesPath;
    /**
     * We could use List also for files variable references and declare them as:
     *
     * private List<File> file = new ArrayList<File>(); private List<String>
     * fileContentType = new ArrayList<String>(); private List<String>
     * fileFileName = new ArrayList<String>();
     */

    private ServletContext context;

    public File[] getFile() {
        return file;
    }

    public void setFile(File[] file) {
        this.file = file;
    }

    public String[] getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String[] fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String[] getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String[] fileFileName) {
        this.fileFileName = fileFileName;
    }

    public void setFilesPath(String filesPath) {
        this.filesPath = filesPath;
    }

    @Override
    public void setServletContext(ServletContext ctx) {
        this.context = ctx;
    }

    @Override
    public String execute() throws IOException {
        String verRes = "";
        System.out.println("No of files=" + getFile().length);
        System.out.println("File Names are:" + Arrays.toString(getFileFileName()));
        final HttpServletRequest request = ServletActionContext.getRequest();
        Boolean resv = GenerateDigitalSignature.verifyUserSigh(getFile()[0], getFile()[1]);
        System.out.println(resv);
        if (resv) {
            verRes = "Signature correct!!";
        } else {
            verRes = "Signature doesn not match!!";
        }
        request.setAttribute("verResult", verRes);
        for (int i = 0; i < getFile().length; i++) {
            System.out.println("File Name is:" + getFileFileName()[i]);
            System.out.println("File ContentType is:" + getFileContentType()[i]);
            System.out.println("Files Directory is:" + filesPath);
            try {
                FileUtil.saveFile(getFile()[i], getFileFileName()[i], "d:/Documents/Varrek/Programs/magwork/Repos/temp/");
            } catch (IOException e) {
                throw e;
                // return INPUT;
            }
        }
        return SUCCESS;

    }

}
