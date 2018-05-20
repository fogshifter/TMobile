package com.tmobile.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;

import java.util.List;


public abstract class GenericDAO<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    public void insert(T entity) {
        entityManager.persist(entity);
    }

    public void update(T entity) {
        entityManager.merge(entity);
    }

    public void remove(T entity) {
        entityManager.remove(entity);
    }
	
	/*public T find(int id) {
//		entityManager.
//		return entityManager.find(entityClass, primaryKey)
	}*/

    public abstract List<T> getAll();
	/*public List<T> getAll() {
		Query query = entityManager.createQuery("select c from " + T.getSimpleClassName() + "");
		
		return query.getResultList();
		
//		ArrayList<T> list = new ArrayList<T>();
	}*/

//	public 
}
