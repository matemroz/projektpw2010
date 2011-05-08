/**
 * Klasa generyczna do tworzenia obiektów dostępu do bazy danych.
 */
package pw.childcontrol.server.database.hb.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class GenericDAO extends HibernateDaoSupport {
	
	public void save(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	}
	
	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}
	
	public List<Object> find(String query) {
		return getHibernateTemplate().find(query);
	}
	
}

