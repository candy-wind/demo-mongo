package com.example.demomongo;


import com.example.demomongo.param.Book;
import com.example.demomongo.param.Person;
import com.example.demomongo.param.PropertyDemo;
import com.example.demomongo.property.UserService;
import com.example.demomongo.springcontext.User;
import crawler.CMRequest;
import crawler.LoginInfo;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

/**
 * @Author candy-wind
 * @Data: 2020-03-18 12:42
 * @Version 1.0
 */
public class TestIoc {


    @Autowired
    WebApplicationContext webApplicationContext;
    @Autowired
    CMRequest cmRequest;

    @Test
    public void testUser(){
////        webApplicationContext.
//        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
//         Book book   =(Book) context.getBean("book");
//        book.demo();
////         User user2   =(User) context.getBean("user");
////        System.out.println(user);
////        System.out.println(user2);
//        System.out.println();
//        user.add();


//        Object o=WebApplicationContextUtils.getWebApplicationContext(webApplicationContext.getServletContext()).getBean("user");
//        System.out.println(o);


//        //注入对象
//        ApplicationContext context =new ClassPathXmlApplicationContext("bean.xml");
//        UserService userService = (UserService) context.getBean("userService");
//        userService.add();
//        System.getProperty("java.class.path");

//        //注入对象 加在spring配置文件创建对象
        ApplicationContext context =new ClassPathXmlApplicationContext("bean.xml");
        Person person = (Person) context.getBean("person");
        person.test1();

//        //注入对象 加在spring配置文件创建对象
//        ApplicationContext context =new ClassPathXmlApplicationContext("bean.xml");
//        UserService userService = (UserService) context.getBean("userService");
//        userService.add();
    }

}
