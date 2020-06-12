package com.example.demomongo.thread;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author candy-wind
 * @Data: 2020-05-15 16:35
 * @Version 1.0
 */
public class ScheduledThreadExecutorTest {

    public static void main(String[] args) {

        ScheduledThreadPoolExecutor exec =new ScheduledThreadPoolExecutor(1);
        //每隔一段时间就触发异常
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() { // TODO Auto-generated method stub //throw new RuntimeException();
                // System.out.println("===================");

            }
        }, 10000, 5000, TimeUnit.MILLISECONDS);

        //每隔一段时间打印系统时间，证明两者是互不影响的
        exec.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() { // TODO Auto-generated method stub
                System.out.println(System.nanoTime());

            }
        }, 10000, 2000, TimeUnit.MILLISECONDS);



    }
}
