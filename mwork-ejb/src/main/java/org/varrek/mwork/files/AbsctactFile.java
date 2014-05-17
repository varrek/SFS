package org.varrek.mwork.files;

import java.util.Date;

import javax.persistence.*;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class AbsctactFile {
	@Id
	@GeneratedValue
	private int p_id;
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_created;
	@Column
	@Temporal(TemporalType.DATE)
	private Date date_modyfied;
	@ManyToOne(targetEntity = Folder.class)
	private Folder parent_location;
	@Column
	private String name;

	/**
	 * @param id
	 * @param date_created
	 * @param date_modyfied
	 * @param parent_location
	 * @param name
	 */
	public AbsctactFile(int p_id, Date date_created, Date date_modyfied,
			Folder parent_location, String name) {
		this.p_id = p_id;
		this.date_created = date_created;
		this.date_modyfied = date_modyfied;
		this.parent_location = parent_location;
		this.name = name;
	}

	public AbsctactFile() {
	}

	/**
	 * @return the date_created
	 */
	public Date getDate_created() {
		return date_created;
	}

	/**
	 * @param date_created
	 *            the date_created to set
	 */
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	/**
	 * @return the date_modyfied
	 */
	public Date getDate_modyfied() {
		return date_modyfied;
	}

	/**
	 * @param date_modyfied
	 *            the date_modyfied to set
	 */
	public void setDate_modyfied(Date date_modyfied) {
		this.date_modyfied = date_modyfied;
	}

	/**
	 * @return the parent_location
	 */
	public Folder getParent_location() {
		return parent_location;
	}

	/**
	 * @param parent_location
	 *            the parent_location to set
	 */
	public void setParent_location(Folder parent_location) {
		this.parent_location = parent_location;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return p_id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int p_id) {
		this.p_id = p_id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
