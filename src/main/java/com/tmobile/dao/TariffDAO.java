package com.tmobile.dao;


import com.tmobile.entity.Tariff;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TariffDAO extends GenericDAO<Tariff> {

    @Override
    public List<Tariff> getAll() {
        return entityManager.createQuery("select t from Tariff t").getResultList();
    }

    public Tariff getDefaultTariff() {
        List<Tariff> tariffs = entityManager.createQuery("select t from Tariff t where t.defaultTariff=true").getResultList();

        if (tariffs.isEmpty()) {
            return null;
        }

        return tariffs.get(0);
    }

    public Tariff find(int id) {
        return entityManager.find(Tariff.class, id);
    }
}
