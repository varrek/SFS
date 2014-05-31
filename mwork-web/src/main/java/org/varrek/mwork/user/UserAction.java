/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.user;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author Varrep
 */
public class UserAction {

    private static final String REQUESTATTRNAME = "result";
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    
    private UserController controller = new UserController();
    private String login;
    private String password;
    private String repassword;
    private String email;
    private String reemail;
    private String fullname;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getRepassword() {
        return repassword;
    }

    public String getEmail() {
        return email;
    }

    public String getReemail() {
        return reemail;
    }

    public String getFullname() {
        return fullname;
    }

    public void setLogin(final String login) {
        this.login = login;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRepassword(final String repassword) {
        this.repassword = repassword;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setReemail(final String reemail) {
        this.reemail = reemail;
    }

    public void setFullname(final String gender) {
        this.fullname = gender;
    }

    public String login() {
        String result;
        final HttpServletRequest request = ServletActionContext.getRequest();
        final boolean loginResult = controller.login(getLogin(), getPassword(), request.getSession().getId());
        if (loginResult) {
            result = SUCCESS;
        } else {
            request.setAttribute(REQUESTATTRNAME, "WrongPassword");
            result = FAILURE;
        }
        return result;
    }

    public String register() throws Exception {
        String result;
        final HttpServletRequest request = ServletActionContext.getRequest();
        final RegistrationResult regResult = controller.register(getLogin(), getPassword(), getRepassword(), getEmail(), getReemail(), getFullname(), getAddress());
        switch (regResult) {
            case RegistrationSuccessfull: {
                result = SUCCESS;
                break;
            }
            case UsernameExists: {
                request.setAttribute(REQUESTATTRNAME, "Such username is allready exists!");
                result = FAILURE;
                break;
            }
            case EmailsDoesntMatch: {
                request.setAttribute(REQUESTATTRNAME, "Entered emails doesn't match!");
                result = FAILURE;
                break;
            }
            case PasswordsDoesntMatch: {
                request.setAttribute(REQUESTATTRNAME, "Entered passwords doesn't match!");
                result = FAILURE;
                break;
            }
            default: {
                result = FAILURE;
                break;
            }
        }
        return result;
    }

    public String logout() {
        controller.logout((User) ServletActionContext.getRequest().getAttribute("currentUser"));
        return SUCCESS;
    }
}
