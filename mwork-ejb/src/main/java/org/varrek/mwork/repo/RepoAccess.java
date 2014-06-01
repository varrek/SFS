package org.varrek.mwork.repo;

import javax.persistence.*;

import org.varrek.mwork.user.User;

@Entity
public class RepoAccess {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne(targetEntity = User.class)
        @JoinColumn(name = "userAc", referencedColumnName = "id")
	private User userAc;
	@ManyToOne(targetEntity = Repo.class)
        @JoinColumn(name = "repoAC", referencedColumnName = "id")
	private Repo repoAC;

	@Column
	private boolean isAdmin;

	@Column
	private boolean isOperator;

	@Column
	private boolean isHaveAccess;

	/**
	 * @param userAc
	 * @param repoAC
	 * @param isAdmin
	 * @param isOperator
	 * @param isHaveAccess
	 */
	public RepoAccess(User userAc, Repo repoAC, boolean isAdmin,
			boolean isOperator, boolean isHaveAccess) {
		this.userAc = userAc;
		this.repoAC = repoAC;
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
	 * @return the userAc
	 */
	public User getUserAc() {
		return userAc;
	}

	/**
	 * @param userAc
	 *            the userAc to set
	 */
	public void setUserAc(User userAc) {
		this.userAc = userAc;
	}

	/**
	 * @return the repoAC
	 */
	public Repo getRepoAC() {
		return repoAC;
	}

	/**
	 * @param repoAC
	 *            the repoAC to set
	 */
	public void setRepoAC(Repo repoAC) {
		this.repoAC = repoAC;
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
