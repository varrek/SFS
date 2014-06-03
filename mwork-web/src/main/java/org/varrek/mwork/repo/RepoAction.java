/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class RepoAction {

    private static final String REQUESTATTRNAME = "result";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    private RepoController controller = new RepoController();
    private String name;
    private String descr;
    private List<RepoAccess> usersRep;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(final String descr) {
        this.descr = descr;
    }

    public List<RepoAccess> getUsersRep() {
        return usersRep;
    }

    public void setUsersRep(final List<RepoAccess> usersRep) {
        this.usersRep = usersRep;
    }
    
    public String createRepo() {
        String result;
        final HttpServletRequest request = ServletActionContext.getRequest();
        final String createResult = controller.createRepository(getName(),getDescr(), (User) request.getAttribute("currentUser"));
        if (createResult=="success") {
            result = SUCCESS;
        } else {
            request.setAttribute(REQUESTATTRNAME, "WrongPassword");
            result = FAILURE;
        }
        return result;
    }
}
