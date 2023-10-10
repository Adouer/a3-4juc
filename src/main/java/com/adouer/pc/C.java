package com.adouer.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *condition 精确通知
 * ABC使用    a通知b b通知c c通知a
 */
public class C {
    public static void main(String[] args) {
        Data3 data3 =new Data3();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printA();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printB();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data3.printC();
            }
        },"C").start();
    }
}

//资源类  属性 方法
class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    private int num = 1;
    public void printA(){
        lock.lock();
        try {
            //业务  ： 判断  执行 通知
            while (num!=1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName()+"-->AAAAAA");
            num = 2;
            //通知b
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printB(){
        lock.lock();
        try {
            //业务  ： 判断  执行 通知
            while (num!=2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName()+"-->BBBBBB");
            num=3;
            //通知b
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void printC(){
        lock.lock();
        try {
            //业务  ： 判断  执行 通知
            while (num!=3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName()+"-->CCCCCCC");
            num = 1;
            //通知b
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}