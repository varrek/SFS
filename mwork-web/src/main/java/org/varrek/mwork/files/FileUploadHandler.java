/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.varrek.mwork.signature.*;
import org.varrek.mwork.signature.GenerateDigitalSignature;

/**
 * Servlet to handle File upload request from Client
 *
 * @author Javin Paul
 */
public class FileUploadHandler extends HttpServlet {

    private final String UPLOAD_DIRECTORY = "d:/Documents/Varrek/Programs/magwork/Repos/test/";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //process only if its multipart content
        if (ServletFileUpload.isMultipartContent(request)) {
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(request);

                for (FileItem item : multiparts) {
                    if (!item.isFormField()) {
                        String name = new File(item.getName()).getName();

                        File tmp = new File(UPLOAD_DIRECTORY + File.separator + name);
                        item.write(tmp);
                        String res = GenerateDigitalSignature.generateSigh(tmp);
                        boolean ver=GenerateDigitalSignature.verifySigh(tmp);
                        request.setAttribute("message", "\nVeryfise:"+ver);
                        item.write(new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                //File uploaded successfully
                //  request.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                request.setAttribute("message", "File Upload Failed due to " + ex);
            }

        } else {
            request.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        request.getRequestDispatcher("/result.jsp").forward(request, response);

    }

}
