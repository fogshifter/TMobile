package com.tmobile.dao;

//import org.hibernate.query.Query;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.tmobile.entity.User;

import java.util.List;


@Repository
public class UserDAO extends GenericDAO<User> {

//	@PersistenceContext
//	protected EntityManager entityManager;
	@Override
	public List<User> getAll() {
		return entityManager.createQuery("select c from User c").getResultList();
	}
	
	public User findById(int id) {
		return entityManager.find(User.class, Integer.valueOf(id));
	}
	
	public User findByEmail(String email) {
		Query selQ = entityManager.createQuery("select c from User c where c.email=:email");
		selQ.setParameter("email", email);
		
		List<User> users = selQ.getResultList();
		
		if(users.isEmpty()) {
			return null;
		}
		
		return users.get(0);
	}
}
