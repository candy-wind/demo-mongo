package com.example.demomongo.springcontext;

/**
 * @Author candy-wind
 * @Data: 2020-03-18 12:40
 * @Version 1.0
 */
public class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public User() {
        super();
    }
    public void add(){
        System.out.println("add....");
    }
}
