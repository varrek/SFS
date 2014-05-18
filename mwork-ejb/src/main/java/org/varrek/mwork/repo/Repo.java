package org.varrek.mwork.repo;

import java.io.File;
import java.util.*;

import javax.persistence.*;


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
    @OneToMany(targetEntity = RepoAccess.class)
    @JoinColumn(name = "repoID")
    private List<RepoAccess> users;
    @OneToOne(targetEntity = Keys.class)
    @JoinColumn(name = "id")
    private Keys keyRepo;
 


    /**
     * @param name
     * @param descr
     * @param users
     * @param keyRepo
     */
    public Repo(String name, String descr, List<RepoAccess> users,
            Keys keyRepo) {
        this.name = name;
        this.descr = descr;
        this.users = users;
        this.keyRepo = keyRepo;
    }

    private Repo() {
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
     * @return the users
     */
    public List<RepoAccess> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<RepoAccess> users) {
        this.users = users;
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

}
