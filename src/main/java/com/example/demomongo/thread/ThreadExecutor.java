package com.example.demomongo.thread;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
//    ThreadPoolTaskExecutor  threadPoolTaskExecutor= ThreadPoolExecutor.




}
