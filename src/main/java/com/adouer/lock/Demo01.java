package com.adouer.lock;

/**
 * 可重入锁
 * 锁中还有锁
 */
public class Demo01 {

    public static void main(String[] args) {
        new Thread(()->{
          Phone.msg();
        },"A").start();
        new Thread(()->{
            Phone.msg();
        },"B").start();
    }

}
class Phone{

    public static synchronized void msg(){
        System.out.println(Thread.currentThread().getName()+ "msg");
        call();
    }
    public static synchronized void call(){
        System.out.println(Thread.currentThread().getName()+ "call");
    }

}