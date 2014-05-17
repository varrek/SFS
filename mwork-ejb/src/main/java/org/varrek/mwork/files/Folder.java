package org.varrek.mwork.files;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class Folder extends AbsctactFile {
	@OneToMany(targetEntity = AbsctactFile.class)
	@JoinColumn(name = "id")
	private List<AbsctactFile> files;

	/**
	 * @param date_created
	 * @param date_modyfied
	 * @param parent_location
	 * @param filess
	 */

	public Folder() {
		this.files = new ArrayList<AbsctactFile>();
	}

	/**
	 * @param id
	 * @param date_created
	 * @param date_modyfied
	 * @param parent_location
	 * @param name
	 * @param filess
	 */
	public Folder(int id, Date date_created, Date date_modyfied,
			Folder parent_location, String name, List<AbsctactFile> files) {
		super(id, date_created, date_modyfied, parent_location, name);
		this.files = files;
	}

	/**
	 * @return the filess
	 */
	public List<AbsctactFile> getFiles() {
		return files;
	}

	/**
	 * @param filess
	 *            the filess to set
	 */
	public void setFiles(List<AbsctactFile> files) {
		this.files = files;
	}

}
