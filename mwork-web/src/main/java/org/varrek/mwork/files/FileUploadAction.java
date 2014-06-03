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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.varrek.mwork.signature.GenerateDigitalSignature;

/**
 *
 * @author Varrep
 */
public class FileUploadAction extends ActionSupport {

    private static final long serialVersionUID = -4748500436762141236L;

    private File[] file;
    private String[] fileContentType;
    private String[] fileFileName;

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

    @Override
    //TODO dont loose request attributes\parametrs while redirect in struts.xml
    public String execute() throws IOException {
        String verRes = "";
        System.out.println("No of files=" + getFile().length);
        System.out.println("File Names are:" + Arrays.toString(getFileFileName()));
        final HttpServletRequest request = ServletActionContext.getRequest();
        Boolean resv = GenerateDigitalSignature.verifyUserSigh(getFile()[0], getFile()[1]);
        if (resv) {
            verRes = "Signature correct!!";
        } else {
            verRes = "Signature doesn not match!!";
        }
        System.out.println(resv);
        if (resv) {
            verRes = "Signature correct!!";
        } else {
            verRes = "Signature doesn not match!!";
        }
        request.setAttribute("verResult", verRes);
        System.out.println(request.getAttribute("verResult"));
        return SUCCESS;

    }

}
