package com.tb.system.dao;

import com.tb.system.bean.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    private UserDaoImpl userDao = UserDaoImpl.getInstance();
    private List<User> list = new ArrayList<>();


    @Before
    public void init() {
        list.clear();
        User user1 = new User();
        user1.password = "1";
        user1.userName = "user1";
        list.add(user1);

        User user2 = new User();
        user2.password = "2";
        user2.userName = "user2";
        list.add(user2);

        User user3 = new User();
        user3.password = "3";
        user3.userName = "user3";
        list.add(user3);
    }

    @Test
    public void create() {
        int[] res=userDao.create(list);
        assertEquals(3,res.length);
    }

    @Test
    public void read() {
        List<User> l=userDao.read("user1");
        assertEquals(1,l.size());
        assertEquals("user1",l.get(0).userName);
    }

    @Test
    public void update() {
        List<User> l=userDao.read("user1");
        l.get(0).nickName="haha";
        int[] i=userDao.update(l);
        assertEquals(1,i.length);
    }

    @Test
    public void delete() {
        List<Object> l=new ArrayList<>();
        l.add("user3");
        l.add("user2");
        int[] i=userDao.delete(l);
        assertEquals(2,i.length);
    }
}