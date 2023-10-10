package com.adouer.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 5.send和call都为 synchronized static,两个线程先输出的是 发短信还是打电话？【发短信】
 *     原因：因为是静态方法，锁的是Class模板，A线程的sendsms先拿到锁
 * 6.两个对象，两个同步方法是先执行发短信还是打电话？【发短信】
 *     原因：同上
 */
public class Test3 {
    public static void main(String[] args) {
        Phone3 phone3 = new Phone3();
        //A线程
        new Thread(Phone3::sendsms,"A").start();
        /*休息1s*/
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //B线程
        new Thread(Phone3::call,"B").start();
    }
}

class Phone3 {
    public static synchronized void sendsms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    public static synchronized void  call(){
        System.out.println("打电话");
    }
}