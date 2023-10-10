package com.adouer.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *CAS【比较并交换】
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2020);

        atomicInteger.getAndIncrement();
        /*============================捣乱线程B开始===================================*/
        System.out.println(atomicInteger.compareAndSet(2021, 2022));
        System.out.println(atomicInteger.compareAndSet(2022, 2021));
        /*============================捣乱线程B结束===================================*/

        /*============================捣乱线程A开始===================================*/
        System.out.println(atomicInteger.compareAndSet(2021, 6666));
        /*============================捣乱线程结束===================================*/
        System.out.println(atomicInteger);
    }
}
