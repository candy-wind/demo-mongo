package com.example.demomongo.property;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author candy-wind
 * @Data: 2020-04-01 18:57
 * @Version 1.0
 */


@Component(value = "userService")
public class UserService {
//    @Autowired
//    private UserDao userDao;
    @Resource(name = "userDao")
    protected UserDao userDao;

    public void add(){
        userDao.add();
        System.out.println("service.......");
    }




    @Test
    public void test(){

    }



}
