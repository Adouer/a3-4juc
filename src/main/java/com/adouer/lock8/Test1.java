package com.adouer.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 1.标准情况下，两个线程先输出的是 发短信还是打电话？【发短信】
 *      因为：锁的是调用同步方法的对象，Phone内方法synchronized修饰，A线程先调用，打短信方法先获取到锁，这里锁的就是new出来的phone对象
 * 2.sendsms加延迟，两个线程先输出的是 发短信还是打电话？【发短信】
 *      原因：同上
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        //A线程
        new Thread(phone::sendsms,"A").start();
        /*休息1s*/
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //B线程
        new Thread(phone::call,"B").start();
    }
}

class Phone {
    public synchronized void sendsms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public synchronized void  call(){
        System.out.println("打电话");
    }
}