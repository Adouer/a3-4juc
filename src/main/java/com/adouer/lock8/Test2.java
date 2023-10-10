package com.adouer.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8锁，就是关于锁的8个问题
 * 3.线程A调send,B调hello，两个线程先输出的是 发短信还是打电话？【发短信】
 *     原因：锁的是调用同步方法的对象 hello方法没有锁，sendsms方法睡4s,所以hello()先执行
 * 4.两个对象，两个同步方法是先执行发短信还是打电话？【打电话】
 *     原因：锁对象不同，相当于没锁，发短信睡了4s,所以是打电话先
 */
public class Test2 {
    public static void main(String[] args) {
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        //A线程
        new Thread(phone1::sendsms,"A").start();
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

class Phone2 {
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
    //这里没有锁
    public void hello(){
        System.out.println("hello");
    }
}