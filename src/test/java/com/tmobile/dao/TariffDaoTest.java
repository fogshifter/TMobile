package com.tmobile.dao;

import com.tmobile.config.HibernateConfig;
import com.tmobile.entity.Tariff;
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
@ContextConfiguration(classes = { HibernateConfig.class })
@TestPropertySource("classpath:application.properties")
@Transactional
public class TariffDaoTest {

    @Autowired
    private TariffDAO dao;

    @Test
    public void getDefaultTariff() {
        Tariff tariff = new Tariff();
        tariff.setDefaultTariff(true);
        dao.insert(tariff);
        Assert.assertEquals(tariff, dao.getDefaultTariff());
    }

    @Test
    @Ignore
    public void remove() throws EntryNotFoundException {
        Tariff tariff1 = new Tariff();
        Tariff tariff2 = new Tariff();
        dao.insert(tariff1);
        dao.insert(tariff2);
        dao.remove(Arrays.asList(tariff1, tariff2));
        Assert.assertNull(dao.findById(tariff1.getId(), Tariff.class));
        Assert.assertNull(dao.findById(tariff2.getId(), Tariff.class));
    }

    @Test
    public void getByIds() {
        Tariff tariff1 = new Tariff();
        Tariff tariff2 = new Tariff();
        dao.insert(tariff1);
        dao.insert(tariff2);
        Assert.assertArrayEquals(Arrays.asList(tariff1, tariff2).toArray(),
                dao.getByIds(Arrays.asList(tariff1.getId(), tariff2.getId())).toArray());
    }
}
