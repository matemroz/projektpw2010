/**
 * Klasa generyczna do tworzenia obiektów dostępu do bazy danych.
 */
package pw.childcontrol.server.database.hb.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GenericDAO extends HibernateDaoSupport {

	public void store(Object object) {
			getHibernateTemplate().saveOrUpdate(object);
	}

	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}

	public List<Object> find(String query) {
		return getHibernateTemplate().find(query);
	}

	public Object findUnique(String query) {
		getSession();
		List<Object> list = getHibernateTemplate().find(query);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}
}
