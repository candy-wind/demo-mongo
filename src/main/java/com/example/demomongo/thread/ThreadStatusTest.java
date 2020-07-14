package com.example.demomongo.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author candy-wind
 * @Data: 2020-04-13 09:58
 * @Version 1.0
 */
public class ThreadStatusTest {
    static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Work("thread1"));
        Thread thread2 = new Thread(new Work("thread2"));

        thread1.start();
        thread2.start();

        try {
            // @1
            Thread.sleep(1);
        }catch(Exception ex){

        }

        System.out.println("thread1状态" + thread1.getState().toString());
        System.out.println("thread2状态" + thread2.getState().toString());

    }

    static class Work implements Runnable{
        private String name;
        public Work(String name){
            this.name = name;
        }
        @Override
        public void run(){
            try {
                lock.lock();
                // @2
                Thread.sleep(1 * 1000);
                System.out.println(name+"执行完成");
            }
            catch(Exception ex){

            } finally {
                lock.unlock();
            }
        }
    }



}
