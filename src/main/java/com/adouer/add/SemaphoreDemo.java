package com.adouer.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量计数器
 * 停车位【有6辆车，只有三个停车位】
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();    //获得
                    System.out.println(Thread.currentThread().getName() + "获得了停车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "离开了停车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();    //释放
                    //System.out.println("剩余车位" + semaphore.availablePermits());
                }
            }, String.valueOf(i)).start();
        }
    }
}
