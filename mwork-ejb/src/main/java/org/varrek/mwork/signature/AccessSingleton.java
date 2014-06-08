/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.util.ArrayList;
import org.varrek.mwork.repo.Repo;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class AccessSingleton {

    private User user;
    private Repo rep;
    ArrayList shares = new ArrayList();

    private static AccessSingleton instance = null;

    protected AccessSingleton() {
        // Exists only to defeat instantiation.
    }

    public static AccessSingleton getInstance() {
        if (instance == null) {
            instance = new AccessSingleton();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Repo getRep() {
        return rep;
    }

    public void setRep(Repo rep) {
        this.rep = rep;
    }

    public ArrayList getShares() {
        return shares;
    }

    public void setShares(ArrayList shares) {
        this.shares = shares;
    }
    
}
