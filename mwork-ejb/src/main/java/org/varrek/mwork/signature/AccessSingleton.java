/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.signature;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.varrek.mwork.repo.Repo;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class AccessSingleton {

    private User user;
    private Repo rep;
    Map <Integer,BigInteger> shares = new HashMap<>();

    public AccessSingleton(User user, Repo rep) {
        this.user = user;
        this.rep = rep;
    }

    public  Map <Integer,BigInteger>  getShares() {
        return shares;
    }

    public void setShares( Map <Integer,BigInteger>  shares) {
        this.shares = shares;
    }

    public void setNewShare(Integer usID,BigInteger code) {
        this.shares.put(usID,code);
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
