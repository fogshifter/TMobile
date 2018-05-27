package com.tmobile.dao;


import com.tmobile.entity.Tariff;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TariffDAO extends GenericDAO<Tariff> {

    public Tariff getDefaultTariff() {
        List<Tariff> tariffs = entityManager.createQuery("select t from Tariff t where t.defaultTariff=true").getResultList();

        if (tariffs.isEmpty()) {
            return null;
        }

        return tariffs.get(0);
    }

    public List<Tariff> getByIds(List<Integer> tariffsIds) {
        return entityManager.createQuery("select t from Tariff t where t.id in :ids").setParameter("ids", tariffsIds).getResultList();
    }

    public void remove(List<Tariff> tariffs) {
        List<Integer> tariffsIds = tariffs.stream().map(opt -> opt.getId()).collect(Collectors.toList());

        entityManager.createQuery("delete from Tariff t where t.id in :ids").setParameter("ids", tariffsIds).executeUpdate();
    }
}
