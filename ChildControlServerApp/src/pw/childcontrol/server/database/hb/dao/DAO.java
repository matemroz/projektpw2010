/**
@author      Mateusz Mróz
@version	 1.0
*/

package pw.childcontrol.server.database.hb.dao;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;

public class DAO {

	public DAO() {
	}

	public static Session getSession() {
		Session session = (Session) DAO.session.get();
		if (session == null) {
			session = sessionFactory.openSession();
			DAO.session.set(session);
		}
		return session;
	}

	/**
	 * Method responsible for beginning Hibernate session.
	 */
	public void begin() {
		getSession().beginTransaction();
	}

	public void commit() {
		getSession().getTransaction().commit();
	}

	public void rollback() {
		try {
			getSession().getTransaction().rollback();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot rollback", e);
		}

		try {
			getSession().close();
		} catch (HibernateException e) {
			log.log(Level.WARNING, "Cannot close", e);
		}
		DAO.session.set(null);
	}

	/**
	 * Method responsible for closing Hibernate session. 
	 */
	public static void close() {
		getSession().close();
		DAO.session.set(null);
	}

	private static final Logger log = Logger.getAnonymousLogger();
	private static final ThreadLocal session = new ThreadLocal();
	private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
}
