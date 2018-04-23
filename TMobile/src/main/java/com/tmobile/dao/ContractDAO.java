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
        Query query = entityManager.createQuery("select c from Contract c where c.customer_id=:customer_id");
        query.setParameter("customer_id", customerId);

        return query.getResultList();
    }
}
