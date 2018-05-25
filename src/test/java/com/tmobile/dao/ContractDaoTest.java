package com.tmobile.dao;

import com.tmobile.config.HibernateConfig;
import com.tmobile.entity.Contract;
import com.tmobile.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { HibernateConfig.class })
@Transactional
public class ContractDaoTest {

    private static final String PHONE = "89997777777";

    @Autowired
    private ContractDAO dao;

    @Autowired
    private UserDAO userDAO;

    @Test
    public void findById() {
        Contract contract = new Contract();
        dao.insert(contract);
        Contract result = dao.findById(1);
        Assert.assertEquals(contract, result);
    }

    @Test
    public void getAll() {
        Contract firstContract = new Contract();
        Contract secondContract = new Contract();
        dao.insert(firstContract);
        dao.insert(secondContract);
        Assert.assertArrayEquals(Arrays.asList(firstContract, secondContract).toArray(), dao.getAll().toArray());

    }

    @Test
    public void getCustomerContracts() {
        Contract contract1 = new Contract();
        Contract contract2 = new Contract();
        User user1 = new User();
        userDAO.insert(user1);
        contract1.setCustomer(user1);
        contract2.setCustomer(user1);
        dao.insert(contract1);
        dao.insert(contract2);
        Assert.assertArrayEquals(Arrays.asList(contract1, contract2).toArray(),
                dao.getCustomerContracts(1).toArray());
    }

    @Test
    public void findByPhone() {
        Contract contract1 = new Contract();
        contract1.setPhone(PHONE);
        dao.insert(contract1);
        Contract contract2 = new Contract();
        contract2.setPhone(PHONE);
        dao.insert(contract2);
        Assert.assertArrayEquals(Arrays.asList(contract1, contract2).toArray(),
                dao.findByPhone(PHONE).toArray());
    }

    @Test
    public void getLast() {
        Contract firstContract = new Contract();
        Contract secondContract = new Contract();
        dao.insert(firstContract);
        dao.insert(secondContract);
        Assert.assertEquals(secondContract, dao.getLast());
    }

}
