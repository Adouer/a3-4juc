package com.adouer.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * 引出volatile(易变的)
 * 两个线程的工作空间没有及时刷新：
 * 导致程序没法停止
 */
public class Demo01 {

    private volatile static int num = 0;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (num == 0) {
            }
        }).start();

        TimeUnit.SECONDS.sleep(5);
        num = 1;
        System.out.println("主线程执行完毕" + num);

    }
}
