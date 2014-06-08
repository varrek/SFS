package org.varrek.mwork.repo;

import java.io.File;
import java.util.*;

import javax.persistence.*;
import org.hibernate.annotations.Cascade;
import org.varrek.mwork.user.User;

@Entity
@Table(name = "repositories")
public class Repo {

    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @Column
    private String descr;
    @Column
    private String prime;
    @OneToMany(targetEntity = RepoAccess.class)
    private List<RepoAccess> usersRep;
    @OneToOne(targetEntity = Keys.class)
    @JoinColumn(name = "id")
    private Keys keyRepo;

    /**
     * @param name
     * @param descr
     * @param keyRepo
     */
    public Repo(String name, String descr,
            Keys keyRepo) {
        this.name = name;
        this.descr = descr;
        this.usersRep = new ArrayList<RepoAccess>();
        this.keyRepo = keyRepo;
    }

    public Repo() {
        this.usersRep = new ArrayList<RepoAccess>();
    }

    public String getPrime() {
        return prime;
    }

    public void setPrime(String prime) {
        this.prime = prime;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the descr
     */
    public String getDescr() {
        return descr;
    }

    /**
     * @param descr the descr to set
     */
    public void setDescr(String descr) {
        this.descr = descr;
    }

    /**
     * @return the usersRep
     */
    public List<RepoAccess> getUsers() {
        return usersRep;
    }

    /**
     * @param users the usersRep to set
     */
    public void setUsers(List<RepoAccess> users) {
        this.usersRep = users;
    }

    /**
     * @return the keyRepo
     */
    public Keys getKeyRepo() {
        return keyRepo;
    }

    /**
     * @param keyRepo the keyRepo to set
     */
    public void setKeyRepo(Keys keyRepo) {
        this.keyRepo = keyRepo;
    }

    public RepoAccess getUserRight(User user) {
        RepoAccess result = null;
        for (RepoAccess curr : usersRep) {
            if (curr.getUserAc().getId() == user.getId()) {
                result = curr;
                break;
            }
        }
        return result;
    }

    public void setUserRight(RepoAccess access) {
        for (RepoAccess curr : usersRep) {
            if (curr.getRepoAC().getId() == access.getRepoAC().getId()) {
                usersRep.remove(curr);
                break;
            }
        }
        usersRep.add(access);
    }

}
