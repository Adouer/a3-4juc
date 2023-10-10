package com.adouer.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 7.sendsms静态，call普通，一个对象,两个线程先输出的是 发短信还是打电话？【发短信】
 *     原因：静态方法锁Class模板，普通方法锁对象，sendsms延迟4s,先打电话
 * 8.sendsms静态，call普通，两个对象，两个同步方法是先执行发短信还是打电话？【发短信】
 *     原因：同上
 */
public class Test4 {
    public static void main(String[] args) {
        Phone4 phone = new Phone4();
        Phone4 phone2 = new Phone4();
        //A线程
        new Thread(Phone4::sendsms,"A").start();
        /*休息1s*/
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //B线程
        new Thread(phone2::call,"B").start();
    }
}

class Phone4 {
    public static synchronized void sendsms(){
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