package com.tmobile.dao;

import com.tmobile.entity.Option;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OptionDAO extends GenericDAO<Option> {

    public List<Option> getByIds(List<Integer> optionIds) {
        return entityManager.createQuery("select o from Option o where o.id in :ids")
                .setParameter("ids", optionIds).getResultList();
    }

    public void remove(List<Option> options) {
        List<Integer> optionsIds = options.stream().map(opt-> opt.getId()).collect(Collectors.toList());
        entityManager.createQuery("delete from Option o where o.id in :ids")
                .setParameter("ids", optionsIds).executeUpdate();
    }
}
