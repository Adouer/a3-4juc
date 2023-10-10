package com.adouer.demo01;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 卖票的例子
 * 真正公司中的开发
 * 线程是一个单独的资源类，没有附属操作
 * 1.属性、方法
 */
public class SaleTicketDemo02 {

    public static void main(String[] args) {
        //并发:多个线程造作同一个资源类（同时读写）
        Ticket2 ticket = new Ticket2();

        //@FunctionalInterface,函数式接口,jdk1.8.Lambda表达式
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}

//三步：
//1。 new ReentrantLock();
//2.lock.lock()加锁
//3. try｛业务代码｝ catch finaly{解锁}
class Ticket2 {
    //属性、 方法
    private int number = 50;
    //new lock锁
    Lock lock = new ReentrantLock();

    //卖票
    public void sale() {
      //  lock.lock();//加锁
        lock.tryLock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + number-- + "票,剩余" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           // lock.unlock();
        }
    }

}