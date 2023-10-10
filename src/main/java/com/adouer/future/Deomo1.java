package com.adouer.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调:CompletableFuture
 *  //异步执行
 *  //成功回调
 *  //失败回调
 */
public class Deomo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        test2();
    }

    /**
     * runAsync 没有返回值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"=>runAsync");
        });
        System.out.println("111");
        completableFuture.get();
    }

    /**
     * supplyAsync 有返回值
     */
    public static void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"=>supplyAsync");
            int i = 10/0;
            return 200;
        });
        //成功
        System.out.println(completableFuture.whenComplete((integer, throwable) -> {
            System.out.println("t==>" + integer);     //第一个参数为返回值
            System.out.println("u==>" + throwable);   //第二个参数为异常信息
        }).exceptionally((e) -> { //失败
            System.out.println(e.getMessage());
            return 500;
        }).get());
    }

}
