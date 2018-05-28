package com.tmobile.dao;

import com.tmobile.config.HibernateConfig;
import com.tmobile.entity.User;
import com.tmobile.exception.EntryNotFoundException;
import com.tmobile.util.Types;
import org.junit.Assert;
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
public class UserDaoTest {

    private static final String EMAIL = "email";
//    private static final String ROLE1 = "ROLE_CUSTOMER";
//    private static final String ROLE2 = "ROLE";
    private static final String NAME = "name";


    @Autowired
    private UserDAO dao;

    @Test
    public void findByEmail() {
        User user = new User();
        user.setEmail(EMAIL);
        dao.insert(user);
        Assert.assertEquals(user, dao.findByEmail(EMAIL));
    }

    @Test
    public void getAllCustomers() {
        User user1 = new User();
        user1.setRole(Types.Role.ROLE_CUSTOMER);
        dao.insert(user1);
        User user2 = new User();
        user2.setRole(Types.Role.ROLE_MANAGER);
        dao.insert(user2);
        Assert.assertArrayEquals(Arrays.asList(user1).toArray(),
                dao.getAllCustomers().toArray());
    }

    @Test
    public void findId() throws EntryNotFoundException {
        User user1 = new User();
        user1.setFirstName(NAME);
        dao.insert(user1);
        User user2 = new User();
        user2.setFirstName(NAME);
        Assert.assertEquals(user1.getId(), dao.findId(user2));
    }
}
