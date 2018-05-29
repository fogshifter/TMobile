package com.tmobile.dao;

import com.tmobile.config.HibernateConfig;
import com.tmobile.entity.Option;
import com.tmobile.exception.EntryNotFoundException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("classpath:application.properties")
@ContextConfiguration(classes = { HibernateConfig.class })
@Transactional
public class OptionDaoTest {

    @Autowired
    private OptionDAO dao;

    @Test
    public void getByIds() throws EntryNotFoundException {
        Option option1 = new Option();
        Option option2 = new Option();
        dao.insert(option1);
        dao.insert(option2);
        Assert.assertArrayEquals(Arrays.asList(option1, option2).toArray(), dao.getByIds(
                Arrays.asList(option1.getId(), option2.getId())).toArray());
    }

    @Test
    @Ignore
    public void remove() throws EntryNotFoundException {
        Option option1 = new Option();
        Option option2 = new Option();
        dao.insert(option1);
        dao.insert(option2);
        dao.remove(Arrays.asList(option1, option2));
        Assert.assertNull(dao.findById( option1.getId(), Option.class));
        Assert.assertNull(dao.findById( option2.getId(), Option.class));
    }
}
