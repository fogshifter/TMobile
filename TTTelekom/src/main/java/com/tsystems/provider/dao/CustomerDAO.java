package com.tsystems.provider.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

import com.tsystems.provider.entity.Customer;

@Repository
public class CustomerDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void add(Customer customer) {
		entityManager.persist(customer);
	}
}
