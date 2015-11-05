package com.keratin.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
/**
 * Hibernate util Session.
 */
public class HibernateUtils {
	private static SessionFactory factory = null;
	private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private static Configuration cfg = new Configuration();
	static {
		try {
			cfg.configure();
			factory = cfg.buildSessionFactory();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡSession����
	 * @return Session����
	 */
	public static Session getSession() {
		Session session = (Session) threadLocal.get();
		if (session == null || !session.isOpen()) {
			if (factory == null) {
				rebuildSessionFactory();
			}
			session = (factory != null) ? factory.openSession() : null;
			threadLocal.set(session);
		}
		return session;
	}
	/**
	 * get SessionFactory class.
	 * @return SessionFactory object
	 */
	public static SessionFactory getSessionFactory() {
		return factory;
	}
	/**
	 * close session.
	 */
	public static void closeSession() {
		Session session = (Session) threadLocal.get();
		threadLocal.remove();
		if (session != null) {
			if (session.isOpen()) {
				session.close();
			}
		}
	}
	/**
	 * Create SessionFactory Object.
	 */
	public static void rebuildSessionFactory() {
		try {
			cfg.configure();
			factory = cfg.buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
