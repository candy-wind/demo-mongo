package com.example.demomongo.property;

import org.springframework.stereotype.Component;

import java.sql.SQLOutput;

/**
 * @Author candy-wind
 * @Data: 2020-04-01 18:57
 * @Version 1.0
 */
@Component(value = "userDao")
public class UserDao {

    public void add(){
        System.out.println("dao.......");
    }

}
