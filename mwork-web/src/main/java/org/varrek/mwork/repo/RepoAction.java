/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.varrek.mwork.signature.AccessSingleton;
import static org.varrek.mwork.signature.GenerateDigitalSignature.getHexString;
import org.varrek.mwork.signature.Storage;
import org.varrek.mwork.user.User;
import org.varrek.mwork.user.UserController;

/**
 *
 * @author Varrep
 */
public class RepoAction {

    private static final String REQUESTATTRNAME = "result";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    private RepoController controller = new RepoController();
    private UserController controllerUser = new UserController();
    private String name;
    private String descr;
    private String users;

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
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
        final String createResult = controller.createRepository(getName(), getDescr(), (User) request.getAttribute("currentUser"));
        if (createResult == "success") {
            result = SUCCESS;
        } else {
            request.setAttribute(REQUESTATTRNAME, "WrongPassword");
            result = FAILURE;
        }
        return result;
    }

    public String addManager() {
        String result = "failure";
        ArrayList<User> userList = new ArrayList();
        String[] users = this.getUsers().split(System.getProperty("line.separator"));
        for (String curr : users) {
            User user = controllerUser.getUserByLogin(curr);
            userList.add(user);
            System.out.println("User login: " + curr);
        }
        final boolean addManagerResult = controller.addManagers(userList, controller.getRepoByName(this.getName()));
        System.out.println("Addmanager result " + addManagerResult);
        if (addManagerResult) {
            result = SUCCESS;
        } else {
            result = FAILURE;
        }
        return result;
    }

    public String askAccess() {
        String result = "failure";
        User user = controllerUser.getUserByLogin(getUsers());
        Repo rep = controller.getRepoByName(getName());
        Storage.getInstance().createAccessRequest(user,rep);
        return SUCCESS;
    }
}
