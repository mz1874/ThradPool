package com.mz.HelloMaven;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


/**
 * 启动多线程的第二种方式
 */
public class ThreadRunnnable {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        },"Mz").start();

        System.out.println(Thread.currentThread().getName());
    }
}
