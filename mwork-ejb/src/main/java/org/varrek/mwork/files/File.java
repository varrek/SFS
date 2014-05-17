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
	public File(int id, Date date_created, Date date_modyfied,
			Folder parent_location, String name, float size) {
		super(id, date_created, date_modyfied, parent_location, name);
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
	 * @param size
	 *            the size to set
	 */
	public void setSize(float size) {
		this.size = size;
	}
}
