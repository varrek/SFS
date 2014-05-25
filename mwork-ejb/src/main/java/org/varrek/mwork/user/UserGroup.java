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
	private List<User> users_in_group;

	public UserGroup() {
		this.users_in_group = new ArrayList<User>();
	}

	/**
	 * @param id
	 * @param name
	 * @param users
	 */
	public UserGroup(int id, String name, List<User> users) {
		this.id = id;
		this.name = name;
		this.users_in_group = users;
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
	 * @return the users_in_group
	 */
	public List<User> getUsers() {
		return users_in_group;
	}

	/**
	 * @param users
	 *            the users_in_group to set
	 */
	public void setUsers(List<User> users) {
		this.users_in_group = users;
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
