package com.mz.HelloMaven;

/**
 * 启动多线程的第一种方式
 */
public class TestThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        TestThread testThread=new TestThread();
        testThread.run();
    }
}
