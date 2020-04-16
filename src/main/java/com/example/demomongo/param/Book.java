package com.example.demomongo.param;

/**
 * @Author candy-wind
 * @Data: 2020-03-18 15:11
 * @Version 1.0
 */
public class Book {
    private String bookname;

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public void demo(){
        System.out.println(bookname);
    }
}
