package com.tmobile.dao;

import com.tmobile.config.HibernateConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class })
@Transactional
public class OptionDaoTest {

    @Autowired
    private OptionDAO dao;

    @Test
    public void getAll() {

    }

    @Test
    public void find() {

    }

    @Test
    public void getByIds() {

    }

    public void remove() {

    }
}
