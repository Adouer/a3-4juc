package com.adouer.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1(); //9194
        test2();    //4661
        //test3();      //376

    }
    //普通程序员
    public static void test1(){
        Long sum=0L;
        Long start=System.currentTimeMillis();

        for (Long i = 1L; i < 10_0000_0000; i++) {
            sum+=i;
        }
        Long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"执行时间："+(end-start));
    }
    //forkJoin分支合并
    public static void test2() throws ExecutionException, InterruptedException {
        Long start=System.currentTimeMillis();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinDemo task = new ForkJoinDemo(0L, 10_0000_0000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);  //提交任务
        Long sum = submit.get();
        Long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"执行时间："+(end-start));
    }
    //Stream并行流
    public static void test3(){
        Long start=System.currentTimeMillis();
        long sum = LongStream.range(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        Long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"执行时间："+(end-start));
    }
}
