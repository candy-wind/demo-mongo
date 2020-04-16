package com.example.demomongo.param;

/**
 * @Author candy-wind
 * @Data: 2020-03-18 15:06
 * @Version 1.0
 */
public class PropertyDemo {
    private String username;
    public PropertyDemo(String username) {
        this.username = username;
    }


    public void test(){
        System.out.println(username);
    }

}
