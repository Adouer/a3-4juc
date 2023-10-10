package com.adouer.add;

import java.util.concurrent.CountDownLatch;

/**
 * 加饭计数器，保证线程都执行完后再向下执行
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"go out");
                countDownLatch.countDown(); //数量-1
            },String.valueOf(i)).start();
        }
        countDownLatch.await();     //等待计数器归零，再继续向下执行
        System.out.println("close door");

    }
}
