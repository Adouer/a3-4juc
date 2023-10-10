package com.adouer.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可重入锁
 * 锁中还有锁
 */
public class Demo02 {

    public static void main(String[] args) {
        new Thread(()->{
          Phone2.msg();
        },"A").start();
        new Thread(()->{
            Phone2.msg();
        },"B").start();
    }

}

class Phone2{

    private static Lock lock = new ReentrantLock();

    /**
     * msg方法调用了call，所以会获取两把锁
     */
    public static void msg(){
        lock.lock();        //锁必须成对出现，加一个必须有对应的解锁，如果不配对，会死锁
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "msg");
            call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public static void call(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+ "call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

}