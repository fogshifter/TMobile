package com.tmobile.dao;

import com.tmobile.entity.Option;
import com.tmobile.exception.EntryNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class OptionDAO extends GenericDAO<Option> {

    public List<Option> getByIds(List<Integer> optionIds) throws EntryNotFoundException {
        List<Option> options = entityManager.createQuery("select o from Option o where o.id in :ids")
                .setParameter("ids", optionIds).getResultList();

        if(options.size() != optionIds.size()) {
            throw new EntryNotFoundException("Options not found");
        }

        return options;
    }

    public void remove(List<Option> options) {
        entityManager.createQuery("delete from Option o where o in :options")
                .setParameter("options", options).executeUpdate();
    }
}
