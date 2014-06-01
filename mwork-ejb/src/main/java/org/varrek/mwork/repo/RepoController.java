/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.io.File;
import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.user.User;

/**
 *
 * @author Varrep
 */
public class RepoController {

    // @todo update Hibernate session for User and Repo 
    public static String createRepository(String repoName, String description, User cUser) {
        String result = "failure";
        Session sess = HibernateUtil.openSession();
        Repo newRepo = new Repo();
        cUser = (User)sess.get(User.class, cUser.getId());
        newRepo.setName(repoName);
        newRepo.setDescr(description);
        RepoAccess newRepoAccess = new RepoAccess(cUser, newRepo, true, true, false);
        try {
            sess.beginTransaction();
            sess.persist(newRepo);
            sess.persist(newRepoAccess);
            cUser.setRepoRight(newRepoAccess);
            newRepo.setUserRight(newRepoAccess);
            sess.update(cUser);
            sess.update(newRepo);
            sess.getTransaction().commit();
            String path = "d:\\Documents\\Varrek\\Programs\\magwork\\Repos";
            File theDir = new File(path + '\\' + repoName);
            System.out.println(theDir);
            theDir.mkdir();
        } catch (Exception e) {
            throw e;
        }
        return result;
    }

    public String grantRights(User userId, Repo repoId, boolean isAdmin,
            boolean isOperator, boolean isHaveAccess) {
        String result = "failure";
        Session sess = HibernateUtil.openSession();
        RepoAccess access = new RepoAccess();
        access.setUserAc(userId);
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
