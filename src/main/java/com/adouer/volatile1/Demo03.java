package com.adouer.volatile1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile不保证原子性，
 * 解决
 */
public class Demo03 {

    //volatile不保证原子性
    private static AtomicInteger num = new AtomicInteger();

    public static void add() {
        num.getAndIncrement();
    }
    public static void main(String[] args) {
        //理论上num应该等于20000，但是由于多线程操作，不保证原子性所以每次的结果都不一样
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    add();
                }
            }).start();
        }
        while (Thread.activeCount()>2) {    //存活的线程数>2 main gc
            Thread.yield();
        }
        System.out.println(num);
    }
}
