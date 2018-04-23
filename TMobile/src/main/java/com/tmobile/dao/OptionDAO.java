package com.tmobile.dao;

import com.tmobile.entity.Option;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionDAO extends GenericDAO<Option> {

    @Override
    public List<Option> getAll() {
        return entityManager.createQuery("select o from Option o").getResultList();
    }

    public Option find(int id) {
        return entityManager.find(Option.class, id);
    }


}
