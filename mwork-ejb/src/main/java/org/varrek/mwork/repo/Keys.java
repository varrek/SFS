package org.varrek.mwork.repo;

import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "keys")
public class Keys {
	@Id
	@GeneratedValue
	private int id;
	@Column
	private String publicKey;
	@Column
	private String secretKey;
	@Column
	@Temporal(TemporalType.DATE)
	private Date validity;

	/**
	 * @param id
	 * @param publicKey
	 * @param secretKey
	 * @param validity
	 */
	public Keys(int id, String publicKey, String secretKey, Date validity) {
		this.id = id;
		this.publicKey = publicKey;
		this.secretKey = secretKey;
		this.validity = validity;
	}

	public Keys() {
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
	 * @return the publicKey
	 */
	public String getPublicKey() {
		return publicKey;
	}

	/**
	 * @param publicKey
	 *            the publicKey to set
	 */
	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	/**
	 * @return the secretKey
	 */
	public String getSecretKey() {
		return secretKey;
	}

	/**
	 * @param secretKey
	 *            the secretKey to set
	 */
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	/**
	 * @return the validity
	 */
	public Date getValidity() {
		return validity;
	}

	/**
	 * @param validity
	 *            the validity to set
	 */
	public void setValidity(Date validity) {
		this.validity = validity;
	}

}
