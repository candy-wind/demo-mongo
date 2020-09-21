package com.example.demomongo.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Author candy-wind
 * @Data: 2020-05-14 17:38
 * @Version 1.0
 */
public class ThreadExecutor {
    ExecutorService executorService =  Executors.newSingleThreadExecutor();//executorService
    ExecutorService executorService1 = Executors.newFixedThreadPool(3);
    ExecutorService executorService2 = Executors.newCachedThreadPool();
    ExecutorService executorService3 = Executors.newScheduledThreadPool(2);
//    ThreadPoolTaskExecutor  threadPoolTaskExecutor=new  ThreadPoolExecutor("","");


    /**
     *异步
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("main函数开始执行");
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===task start===");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("===task finish===");
            }
        });

        thread.start();
        System.out.println("main函数执行结束");
    }

}
