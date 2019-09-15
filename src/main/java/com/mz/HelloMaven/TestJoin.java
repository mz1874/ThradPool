package com.mz.HelloMaven;

import java.util.concurrent.TimeUnit;

public class TestJoin {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join(2);
        System.out.println(Thread.currentThread().getName() + "结束");
    }
}
