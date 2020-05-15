package com.example.demomongo.thread;

import java.net.SocketTimeoutException;

/**
 * @Author candy-wind
 * @Data: 2020-05-14 17:42
 * @Version 1.0
 */
public class MyThread extends Thread {


    @Override
    public void run(){

        System.out.println(Thread.currentThread().getName()+"正在执行....");

    }

    public static void main(String[] args) {
        MyThread test1 = new MyThread();
        test1.start();
    }

}
