package com.example.demomongo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author candy-wind
 * @Data: 2020-05-15 16:26
 * @Version 1.0
 */
public class FixedThreadExecutorTest {

    public static void main(String[] args) {
        ExecutorService pool= Executors.newFixedThreadPool(2); //创建实现了Runnable接口对象，Thread对象当然也实现了Runnable接口;

        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        MyThread myThread3 = new MyThread();
        MyThread myThread4= new MyThread();

        pool.execute(myThread);
        pool.execute(myThread1);
        pool.execute(myThread2);
        pool.execute(myThread3);
        pool.execute(myThread4);
        pool.shutdown();
    }

}
