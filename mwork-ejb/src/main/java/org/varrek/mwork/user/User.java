package org.varrek.mwork.user;

import java.util.*;

import javax.persistence.*;

import org.varrek.mwork.repo.Keys;
import org.varrek.mwork.repo.RepoAccess;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private int id;

	@Column
	private String fullName;

	@Column(unique = true)
	private String login;
	@Column
	private String email;
	@Column
	private String address;
	@Column
	private String description;
	@Column
	private String pass;

	@ManyToOne(targetEntity = UserGroup.class)
	private UserGroup group;
	@OneToMany
	@JoinColumn(name = "userId")
	private List<RepoAccess> repositories;

	@OneToOne(targetEntity = Keys.class)
	@JoinColumn(name = "id")
	private Keys keyUser;

	/**
	 * @param id
	 * @param fullName
	 * @param login
	 * @param email
	 * @param address
	 * @param description
	 * @param pass
	 * @param group
	 * @param repositories
	 * @param keyUser
	 */
	public User(int id, String fullName, String login, String email,
			String address, String description, String pass, UserGroup group,
			List<RepoAccess> repositories, Keys keyUser) {
		this.id = id;
		this.fullName = fullName;
		this.login = login;
		this.email = email;
		this.address = address;
		this.description = description;
		this.pass = pass;
		this.group = group;
		this.repositories = repositories;
		this.keyUser = keyUser;
	}

	public User() {
		this.repositories = new ArrayList<RepoAccess>();
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
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName
	 *            the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pass
	 */
	public String getPass() {
		return pass;
	}

	/**
	 * @param pass
	 *            the pass to set
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}

	/**
	 * @return the group
	 */
	public UserGroup getGroup() {
		return group;
	}

	/**
	 * @param group
	 *            the group to set
	 */
	public void setGroup(UserGroup group) {
		this.group = group;
	}

	/**
	 * @return the repositories
	 */
	public List<RepoAccess> getRepositories() {
		return repositories;
	}

	/**
	 * @param repositories
	 *            the repositories to set
	 */
	public void setRepositories(List<RepoAccess> repositories) {
		this.repositories = repositories;
	}

	/**
	 * @return the keyUser
	 */
	public Keys getKeyUser() {
		return keyUser;
	}

	/**
	 * @param keyUser
	 *            the keyUser to set
	 */
	public void setKeyUser(Keys keyUser) {
		this.keyUser = keyUser;
	}

}
