package org.varrek.mwork.user;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.varrek.mwork.HibernateUtil;
import org.varrek.mwork.repo.RepoController;
import org.varrek.mwork.signature.GenerateDigitalSignature;

/**
 * Servlet implementation class User
 */
public class UserController {

    public static class UserSessionBindings {

        private User user;
        private String session;

        public User getUser() {
            return user;
        }

        public String getSession() {
            return session;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public void setSession(String session) {
            this.session = session;
        }
    }
    private static ArrayList<UserSessionBindings> usersOnline;

    /**
     * Connects to the database, used to store passwords (All necessary tables,
     * in fact - simple table Users must be already created).
     *
     * @return True if connection was successfully established.
     */
    public UserController() {
        if (usersOnline == null) {
            usersOnline = new ArrayList<>();
        }
    }

    /**
     * Generates md5 hash code for string.
     *
     * @param str String to be hashed.
     * @return MD5 code.
     */
    public static String getMD5(String str) {
        MessageDigest md5;
        StringBuilder hexString = new StringBuilder();
        try {
            md5 = MessageDigest.getInstance("md5");
            md5.reset();
            md5.update(str.getBytes());
            byte messageDigest[] = md5.digest();
            for (byte element : messageDigest) {
                hexString.append(Integer.toHexString(0xFF & element));
            }
        } catch (NoSuchAlgorithmException e) {
            return e.toString();
        }
        return hexString.toString();
    }

    /**
     * Retrieves user's information by it's Unique ID, except for session ID and
     * Activation code.
     *
     * @param iD ID of the user
     * @return Object of class User, if user exists and null pointer if doesn't
     */
    public static User getUserByID(Integer iD) {
        User result;
        Session sess = HibernateUtil.openSession();
        result = (User) (sess.get(User.class, iD));
        return result;
    }

    /**
     * Function checks, is entered fields data is correct, and if so, registers
     * new user.
     *
     * @param login New user's login.
     * @param password New user's password.
     * @param passwordConfirmation Re-typed user's password.
     * @param email New user's email.
     * @param emailConfitmation Re-typed user's e-mail.
     * @param fullanme
     * @param address
     * @return Registration status.
     * @throws java.lang.Exception
     */
    public RegistrationResult register(
            String login,
            String password, String passwordConfirmation,
            String email, String emailConfitmation, String fullanme, String address) throws Exception {
        RegistrationResult result = RegistrationResult.RegistrationSuccessfull;
        Session sess = HibernateUtil.openSession();
        Query q = sess.createQuery("from User WHERE login=:login");
        List users = q.setParameter("login", login).list();
        if (!users.isEmpty()) {
            result = RegistrationResult.UsernameExists;
        } else if (!password.equals(passwordConfirmation)) {
            result = RegistrationResult.PasswordsDoesntMatch;
        } else if (!email.equals(emailConfitmation)) {
            result = RegistrationResult.EmailsDoesntMatch;
        } else if (login.isEmpty() || password.isEmpty() || email.isEmpty()) {
            result = RegistrationResult.NotAllFieldsFilled;
        } else {
            String HashedPassword = getMD5(password);
            User newUser = new User();
            newUser.setLogin(login);
            newUser.setPass(HashedPassword);
            newUser.setEmail(email);
            newUser.setFullName(fullanme);
            newUser.setAddress(address);
            newUser.setDescription("");
            newUser.setU_group(null);
            try {
                sess.beginTransaction();
                sess.persist(newUser);
                sess.getTransaction().commit();
                sess.close();
                GenerateDigitalSignature.generateKeys(newUser.getLogin());
                RepoController.createRepository(login, "Base repository", newUser);
            } catch (Exception e) {
                throw e;
            }
        }
        return result;
    }

    /**
     * This function logins into the system. If this operation was successful,
     * returns object of class User, that represents the user you are logged as.
     *
     * @param Username Login
     * @param Password Password
     * @param SessionID
     * @return Object of class User, if login was successful, and false in other
     * case.
     */
    @SuppressWarnings("unchecked")
    public boolean login(String Username, String Password, String SessionID) {
        boolean result = true;
        User newUserInstance;
        Session session = HibernateUtil.openSession();
        Query pq = session.createQuery("from User WHERE login='" + Username + "' AND pass='" + getMD5(Password) + "'");
        List<User> queryResult = pq.list();
        if (queryResult.size() == 1) {
            newUserInstance = queryResult.get(0);
            UserSessionBindings newUser = new UserSessionBindings();
            newUser.setUser(newUserInstance);
            newUser.setSession(SessionID);
            usersOnline.add(newUser);
        } else {
            result = false;
        }
        session.close();
        return result;
    }

    /**
     * Closes session for specified user, if it have opened session (doesn't
     * matter, if it's up-to-date or not).
     *
     * @param user User, whose session must be closed.
     * @return True, if session was closed, or false, if error was occurred
     * during attempt.
     */
    public boolean logout(User user) {
        for (UserSessionBindings currenBinding : usersOnline) {
            if (currenBinding.user.equals(user)) {
                usersOnline.remove(currenBinding);
                return true;
            }
        }
        return false;
    }

    /**
     * Allows users to change passwords.
     *
     * @param user
     * @param oldPassword
     * @param newPassword
     * @param newPasswordConfirm
     * @return
     */
    public boolean changePassword(User user, String oldPassword, String newPassword, String newPasswordConfirm) {
        boolean result = false;
        if (newPassword.equals(newPasswordConfirm)) {
            Session sess = HibernateUtil.openSession();
            user
                    = (User) sess.get(User.class, user.getId());
            if (user.getPass()
                    .equals(getMD5(oldPassword))) {
                user.setPass(getMD5(newPassword));
                sess.flush();
            }

            sess.close();
        }
        return result;
    }

    /**
     * Retrieves information about current logged in user from it's IP address.
     *
     * @param sessionID
     * @return Object of type User, which represents current logged in user(If
     * exists).
     */
    public User getCurrentUser(String sessionID) {
        User result = null;
        if (!usersOnline.isEmpty()) {
            for (UserSessionBindings currentBinding : usersOnline) {
                if (currentBinding.session.equals(sessionID)) {
                    Session sess = HibernateUtil.openSession();
                    sess.refresh(currentBinding.user);
                    result = currentBinding.user;
                    break;
                }
            }
        }
        return result;
    }
}
