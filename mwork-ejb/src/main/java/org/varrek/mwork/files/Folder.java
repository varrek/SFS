package org.varrek.mwork.files;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

public class Folder extends AbsctactFile {

    @OneToMany(targetEntity = AbsctactFile.class)
    @JoinColumn(name = "p_id")
    private List<AbsctactFile> files;

    public Folder() {
        this.files = new ArrayList<>();
    }

    /**
     * @param date_created
     * @param date_modyfied
     * @param parent_location
     * @param name
     * @param files
     */
    public Folder( Date date_created, Date date_modyfied,
            Folder parent_location, String name, List<AbsctactFile> files) {
        super(date_created, date_modyfied, parent_location, name);
        this.files = files;
    }

    /**
     * @return the filess
     */
    public List<AbsctactFile> getFiles() {
        return files;
    }

    /**
     * @param files the filess to set
     */
    public void setFiles(List<AbsctactFile> files) {
        this.files = files;
    }

    public String getPath() {
        String result = this.getName();
        if (this.getParent_location() != this) {
            Folder currentParent = this.getParent_location();
            while (currentParent.getParent_location() != currentParent) {
                result = currentParent.getName() + '\\' + result;
                currentParent = currentParent.getParent_location();
            }

            result = currentParent.getName() + '\\' + result;
        }
        result = '\\' + result;
        return result;
    }

}
