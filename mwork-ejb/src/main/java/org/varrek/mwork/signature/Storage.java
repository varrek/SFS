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
public class Storage {

    private static Storage instance = null;
    private ArrayList<AccessSingleton> access = new ArrayList();

    protected Storage() {
        // Exists only to defeat instantiation.
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public ArrayList<AccessSingleton> getAccess() {
        return access;
    }

    public void setAccess(ArrayList<AccessSingleton> access) {
        this.access = access;
    }

    public AccessSingleton getAccessRequest(User user, Repo rep) {
        AccessSingleton result = null;
        for (AccessSingleton curr : access) {
            if ((curr.getUser().getId() == user.getId()) && (curr.getRep().getId() == rep.getId())) {
                result = curr;
            }
        }
        return result;
    }

    public void createAccessRequest(User user, Repo rep) {
        AccessSingleton result = new AccessSingleton(user, rep);
        access.add(result);
    }
}
