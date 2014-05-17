package org.varrek.mwork;

import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.varrek.mwork.user.User;
import org.varrek.mwork.user.UserController;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	static {
		System.out.println("!&!&!");
		try {
			// Configuration configuration = new Configuration();
			Configuration configuration = new Configuration().configure("META-INF/hibernate.cfg.xml");
			Map settings = configuration.getProperties();
			sessionFactory = configuration.buildSessionFactory();
		} catch (HibernateException he) {
			System.err.println("Error creating Session: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private static UserController users;
	private static User currentUser;

	public static Session openSession() {
		return getSessionFactory().openSession();
	}

	public static UserController getUsers() {
		if (users == null)
			users = new UserController();
		return users;
	}

	public static User getCurrentUser() {
		return currentUser;
	}
}
