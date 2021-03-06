package com.tmobile.dao;

import com.tmobile.exception.EntryNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
	
	public T findById(int id, Class<T> tClass) throws EntryNotFoundException {
		T entity = entityManager.find(tClass, id);
		if(entity == null) {
		    throw new EntryNotFoundException(tClass.getSimpleName() + " not found");
        }

        return entity;
	}

    public List<T> getAll(Class<T> tClass) {
		return entityManager.createQuery("select c from " + tClass.getSimpleName() + " c")
                .getResultList();
	}
}
