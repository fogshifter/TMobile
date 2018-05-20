package com.tmobile.dao;

import com.tmobile.entity.Contract;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class ContractDAO extends GenericDAO<Contract> {

    public List<Contract> getAll() {
        return entityManager.createQuery("select c from Contract c").getResultList();
    }

    public List<Contract> getCustomerContracts(int customerId) {
        Query query = entityManager.createQuery("select c from Contract c where c.customer.id=:customer_id");
        query.setParameter("customer_id", customerId);

        return query.getResultList();
    }

    public Contract findById(int id) {
        return entityManager.find(Contract.class, id);
    }

    public List<Contract> findByPhone(String phone) {
        Query query = entityManager.createQuery("select c from Contract c where c.phone like :phone");

        String param = String.join("", "%", phone, "%");
        query.setParameter("phone", param);
        return query.getResultList();
    }

    public Contract getLast() {
        return (Contract) entityManager.createQuery("select c from Contract c order by c.id desc").getResultList().get(0);

    }
}
