package com.example.demomongo.ioc;

/**
 * @Author candy-wind
 * @Data: 2020-03-20 19:01
 * @Version 1.0
 */

//使用spring注入对象 seriece调用dao
//第一种方案
    //具体实现
    //1 在sercice里面你把dao作为类型属性
    //生产dao类型属性的set方法
public class UserService1 {

    private UserDao1 userDao;
    public void setUserDao(UserDao1 userDao) {
        this.userDao = userDao;
    }

    public void add(){
        System.out.println("service");
//        UserDao userDao =new UserDao();
        userDao.add();

    }

    public static void main(String[] args) {
        
    }
}
