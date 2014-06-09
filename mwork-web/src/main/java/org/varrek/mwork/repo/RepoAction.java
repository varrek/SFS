/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.signature.AccessSingleton;
import org.varrek.mwork.signature.SecretShare;
import static org.varrek.mwork.signature.GenerateDigitalSignature.getHexString;
import org.varrek.mwork.signature.Shamir;
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
    private String code;
    private String descr;
    private String users;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

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
        Session sess = HibernateUtil.openSession();
        sess.beginTransaction();
        User user = controllerUser.getUserByLogin(getUsers());
        Repo rep = controller.getRepoByName(getName());
        sess.update(rep);
        for (User curr : rep.getRepModer()) {
            curr.setMessages("User " + user.getLogin() + " want access for Repo"
                    + rep.getName() + ". <a href='/grantRights.jsp?users=" + user.getLogin()
                    + "&name=" + rep.getName() + "'>Grant rights</a>");
            sess.update(curr);
        }
        sess.getTransaction().commit();
        sess.close();
        Storage.getInstance().createAccessRequest(user, rep);
        return SUCCESS;
    }

    public String grantRights() {
        User user = controllerUser.getUserByLogin(getUsers());
        User cuser = (User) ServletActionContext.getRequest().getAttribute("currentUser");
        Repo rep = controller.getRepoByName(getName());
        Session sess = HibernateUtil.openSession();
        sess.update(rep);
        Integer pos = -1;
        int i = 0;
        for (User curr : rep.getRepModer()) {
            if (curr.getId() == cuser.getId()) {
                pos = i;
                break;
            }
            i++;
        }
        System.out.println("uPos" + pos);
        BigInteger bInt = new BigInteger(getCode());
        AccessSingleton storage = Storage.getInstance().getAccessRequest(user, rep);
        storage.setNewShare(pos, bInt);
        int expectCodeCount = rep.getRepModer().size() - 2;
        int actualCodeCount = storage.getShares().size();
        System.out.println(expectCodeCount);
        System.out.println(actualCodeCount);
        System.out.println(storage);
        if (expectCodeCount == actualCodeCount) {
            Map<Integer, BigInteger> sh = storage.getShares();
            final Shamir shamir = new Shamir(rep.getRepModer().size() - 2, rep.getRepModer().size() - 1);
            SecretShare[] shares = new SecretShare[sh.size()];
            final BigInteger prime = new BigInteger(rep.getPrime());
            i = 0;
            for (Map.Entry<Integer, BigInteger> entry : sh.entrySet()) {
                Integer key = entry.getKey();
                System.out.println("Key: " + key);
                BigInteger value = entry.getValue();
                System.out.println("value: " + value);
                SecretShare myShare = new SecretShare(key, value, shamir);
                shares[i] = myShare;
                i++;
            }
            final BigInteger result = shamir.combine(shares, prime);
            System.out.println("Prime from db: " + prime);
            System.out.println("New Result is " + result);
            System.out.println("Secret in DB " + rep.getSecret());
        }
        return SUCCESS;
    }
}
