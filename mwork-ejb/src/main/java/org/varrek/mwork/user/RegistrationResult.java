package org.varrek.mwork.user;

/**
 * Enumeration used to explain, what happened on trying to register new user.
 */
public enum RegistrationResult {
	/**
	 * Everything OK.
	 */
	RegistrationSuccessfull,
	/**
	 * THere is already user with requested name in database. Please, specify other username.
	 */
	UsernameExists,
	/**
	 * Password doesn't match requirements.
	 */
	PasswordWrong,
	/**
	 * Entered password doesn't match it's repeat.
	 */
	PasswordsDoesntMatch,
	/**
	 * Returned, if email doesn't match email mask.
	 */
	IncorrectEmail,
	/**
	 * Entered Email confirmation doesn't match entered email.
	 */
	EmailsDoesntMatch,
	/**
	 * Some of the required information wasn't given on registration procedure.
	 */
	NotAllFieldsFilled,
	/**
	 * Database error acquired during registration. Please try again or contact administrator.
	 */
	DatabaseError
}
