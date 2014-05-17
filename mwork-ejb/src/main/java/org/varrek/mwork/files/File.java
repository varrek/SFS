package org.varrek.mwork.files;

import java.util.Date;

import javax.persistence.*;

@Entity
public class File extends AbsctactFile {

    @Column
    private float size;

    /**
     * @param id
     * @param date_created
     * @param date_modyfied
     * @param parent_location
     * @param name
     * @param size
     */
    public File(Date date_created, Date date_modyfied,
            Folder parent_location, String name, float size) {
        super(date_created, date_modyfied, parent_location, name);
        this.size = size;
    }

    public File() {
    }

    /**
     * @return the size
     */
    public float getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(float size) {
        this.size = size;
    }

    public String getPath() {
        String result = this.getName();
        Folder currentParent = this.getParent_location();
        while (currentParent.getParent_location() == currentParent) {
            result = currentParent.getName() + '\\' + result;
            currentParent = currentParent.getParent_location();
        }
        result = currentParent.getName() + '\\' + result;
        result='\\'+result;
        return result;
    }
}
