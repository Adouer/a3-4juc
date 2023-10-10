package com.adouer.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Executors 3 大方法体会
 */
public class Demo1 {
    public static void main(String[] args) {
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();  //单个线程
        //ExecutorService threadPool = Executors.newFixedThreadPool(5); //固定大小的线程池
       // ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(2);   //创建一个定时及周期性任务执行的线程池，多数情况下替代Timer类
       ExecutorService threadPool = Executors.newCachedThreadPool(); //可伸缩变化的线程池

        try {
            for (int i = 0; i <100 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+":==>ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();  //关闭线程池
        }
    }
}
