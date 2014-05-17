package org.varrek.mwork.user;

import java.util.*;

import javax.persistence.*;

@Entity
public class UserGroup {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@OneToMany(targetEntity = User.class)
        @JoinColumn(name="id")
	private List<User> users;

	/**
	 * @param id
	 * @param users
	 */
	public UserGroup() {
		this.users = new ArrayList<User>();
	}

	/**
	 * @param id
	 * @param name
	 * @param users
	 */
	public UserGroup(int id, String name, List<User> users) {
		this.id = id;
		this.name = name;
		this.users = users;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * @param users
	 *            the users to set
	 */
	public void setUsers(List<User> users) {
		this.users = users;
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
