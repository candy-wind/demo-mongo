package com.example.demomongo.thread;


/**
 * @Author candy-wind
 * @Data: 2020-05-14 17:42
 * @Version 1.0
 *
 * sleep和interrupt中断
 */
public class MyThread extends Thread {
    volatile boolean stop = false;

    @Override
    public void run(){

        System.out.println(Thread.currentThread().getName()+"正在执行....");
        while (!stop) {
            System.out.println(getName() + " is running");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("week up from blcok...");
                stop = true; // 在异常处理代码中修改共享变量的状态 }
            }
            System.out.println(getName() + " is exiting...");
        }

    }

    public static void main(String[] args) {
        MyThread m1 = new MyThread();

        System.out.println("Starting thread...");
        m1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Interrupt thread...: " + m1.getName()); m1.stop = true; // 设置共享变量为true
        m1.interrupt(); // 阻塞时退出阻塞状态
//        m1.wait();
        try {
            Thread.sleep(3000); // 主线程休眠3秒以便观察线程m1的中断情况
             System.out.println("Stopping application...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
