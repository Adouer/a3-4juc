package com.adouer.pc;

/**
 * 线程之间的通信问题：生产着消费者问题 【等待唤醒，通知唤醒】
 * ABCD使用synchronized
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}

// 判断等待、业务、通知[if改成while解决虚假唤醒问题]
class Data { //资源类  【属性，方法】

    private int number = 0;

    //+1
    public synchronized void increment() throws InterruptedException {
        //if (number != 0) {
        while (number != 0) {
            // 等 待
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+ "-->"+ number);
        // 通知其他线程，我+1完成了
        this.notify();
    }
    //-1
    public synchronized void decrement() throws InterruptedException {
        //if (number == 0) {
        while (number == 0) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+ "-->"+ number);
        // 通知其他线程，我-1完成了
        this.notify();
    }
}
