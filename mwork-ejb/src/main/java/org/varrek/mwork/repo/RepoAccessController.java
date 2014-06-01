/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class RepoAccessController {

    public String grantRights(User userId, Repo repoId, boolean isAdmin,
            boolean isOperator, boolean isHaveAccess) {
        String result = "failure";
        Session sess = HibernateUtil.openSession();
        RepoAccess access = new RepoAccess();
       // access.setUser(userId);
        access.setRepoAC(repoId);
        access.setAdmin(isAdmin);
        access.setOperator(isOperator);
        access.setHaveAccess(isHaveAccess);
        try {
            sess.beginTransaction();
            sess.persist(access);
            sess.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        } finally {
            result = "success";
        }
        return result;
    }
}
