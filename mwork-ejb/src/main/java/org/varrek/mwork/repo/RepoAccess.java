package org.varrek.mwork.repo;

import javax.persistence.*;

import org.varrek.mwork.user.User;

@Entity
public class RepoAccess {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity = User.class)
	private User user;
	@ManyToOne(targetEntity = Repo.class)
	private Repo repoID;

	@Column
	private boolean isAdmin;

	@Column
	private boolean isOperator;

	@Column
	private boolean isHaveAccess;

	/**
	 * @param id
	 * @param userId
	 * @param repoID
	 * @param isAdmin
	 * @param isOperator
	 * @param isHaveAccess
	 */
	public RepoAccess(User userId, Repo repoID, boolean isAdmin,
			boolean isOperator, boolean isHaveAccess) {
		this.user = userId;
		this.repoID = repoID;
		this.isAdmin = isAdmin;
		this.isOperator = isOperator;
		this.isHaveAccess = isHaveAccess;
	}

	public RepoAccess() {
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
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param userId
	 *            the user to set
	 */
	public void setUser(User userId) {
		this.user = userId;
	}

	/**
	 * @return the repoID
	 */
	public Repo getRepoID() {
		return repoID;
	}

	/**
	 * @param repoID
	 *            the repoID to set
	 */
	public void setRepoID(Repo repoID) {
		this.repoID = repoID;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the isOperator
	 */
	public boolean isOperator() {
		return isOperator;
	}

	/**
	 * @param isOperator
	 *            the isOperator to set
	 */
	public void setOperator(boolean isOperator) {
		this.isOperator = isOperator;
	}

	/**
	 * @return the isHaveAccess
	 */
	public boolean isHaveAccess() {
		return isHaveAccess;
	}

	/**
	 * @param isHaveAccess
	 *            the isHaveAccess to set
	 */
	public void setHaveAccess(boolean isHaveAccess) {
		this.isHaveAccess = isHaveAccess;
	}

}
