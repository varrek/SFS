/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.math.BigInteger;
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
    ArrayList <BigInteger> shares = new ArrayList();

    public AccessSingleton(User user, Repo rep) {
        this.user = user;
        this.rep = rep;
    }

    public ArrayList<BigInteger> getShares() {
        return shares;
    }

    public void setShares(ArrayList<BigInteger> shares) {
        this.shares = shares;
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
    
}
