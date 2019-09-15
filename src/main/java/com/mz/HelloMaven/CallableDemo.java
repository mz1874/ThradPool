package com.mz.HelloMaven;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


/**
 * 启动多线程的第三种方式 实现Callable接口
 */
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask=new FutureTask<String>(()->{
            System.out.println(Thread.currentThread().getName());
            return "张三";
        });
        Thread thread=new Thread(futureTask);
        thread.start();
        String s = futureTask.get();
        System.out.println(s);
        System.out.println("主线程结束");
    }

}
