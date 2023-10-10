package com.adouer.bq;

import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列，只能放一个元素，放入后放必须取出去才能继续
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {

        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();


        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName()+":put a");
                synchronousQueue.put("a");
                System.out.println(Thread.currentThread().getName()+":put b");
                synchronousQueue.put("b");
                System.out.println(Thread.currentThread().getName()+":put c");
                synchronousQueue.put("c");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"T1").start();

        new Thread(() -> {

            try {
                System.out.println(Thread.currentThread().getName()+":take==>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+":take==>"+synchronousQueue.take());
                System.out.println(Thread.currentThread().getName()+":take==>"+synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"T2").start();
    }
}

