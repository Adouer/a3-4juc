package com.adouer.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *  原子引用解决ABA问题
 *  模拟两个线程
 *  睡两秒保证两个线程都同时执行，而不是A执行完，B才执行
 */
public class CASDemo2 {
    public static void main(String[] args) {

        AtomicStampedReference<Integer> reference = new AtomicStampedReference<>(1,1);

        new Thread(() -> {
            System.out.println("A1==>"+reference.getStamp());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //四个参数：期望的值，比较并交换的值，初始版本号，初始版本号+1
            System.out.println(reference.compareAndSet(1, 2, reference.getStamp(), reference.getStamp() + 1));
            System.out.println("A2==>"+reference.getStamp());
            System.out.println(reference.compareAndSet(2, 1, reference.getStamp(), reference.getStamp() + 1));
            System.out.println("A3==>"+reference.getStamp());

        },"A").start();

        new Thread(() -> {
            int stamp = reference.getStamp();
            System.out.println("B1==>"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //四个参数：期望的值，比较并交换的值，初始版本号，初始版本号+1
            System.out.println(reference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("B2==>"+reference.getStamp());
        },"B").start();
    }
}
