/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.varrek.mwork.repo;

import java.io.File;
import java.math.BigInteger;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.signature.Shamir;
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
        cUser = (User) sess.get(User.class, cUser.getId());
        newRepo.setName(repoName);
        newRepo.setDescr(description);
        RepoAccess newRepoAccess = new RepoAccess(cUser, newRepo, true, true, true);
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
            result = "success";
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

    public static Repo getRepoByName(String name) {
        Repo result;
        Session sess = HibernateUtil.openSession();
        Query q = sess.createQuery("from Repo WHERE name=:name");
        List users = q.setParameter("name", name).list();
        if (users.size() == 0) {
            result = null;
        } else {
            result = (Repo) users.get(0);
        }
        System.out.println("Repository to manage: " + result);
        sess.close();
        return result;
    }

    public boolean addManagers(List<User> users, Repo rep) {
        boolean result = false;
        Session sess = HibernateUtil.openSession();
        sess.update(rep);
        sess.beginTransaction();
        final Shamir shamir = new Shamir(users.size() - 1, users.size());

        final BigInteger secret = new BigInteger("1234567890123456789012345678901234567890");
        final Shamir.SecretShare[] shares = shamir.split(secret);
        final BigInteger prime = shamir.getPrime();
        rep.setPrime(prime.toString());
        int i = 0;
        try {
            for (User curr : users) {
                RepoAccess newRepoAccess = new RepoAccess(curr, rep, false, true, true);
                curr.setMessages("Code: " + shares[i].toString() + " for Repo" + rep.getName());
                sess.update(curr);
                i++;
                sess.persist(newRepoAccess);
                rep.setUserRight(newRepoAccess);
            }
            sess.update(rep);
            sess.getTransaction().commit();
        } catch (Exception e) {
            sess.getTransaction().rollback();
            throw e;
        }
        return result;
    }
}
