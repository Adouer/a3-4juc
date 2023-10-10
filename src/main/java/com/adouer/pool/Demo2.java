package com.adouer.pool;

import java.util.concurrent.*;

/**
 * 银行业务模型体会
 * 七大属性
 */
public class Demo2 {
    public static void main(String[] args) {
        //int corePoolSize,
        //                              int maximumPoolSize,
        //                              long keepAliveTime,
        //                              TimeUnit unit,
        //                              BlockingQueue<Runnable> workQueue,
        //                              ThreadFactory threadFactory,
        //                              RejectedExecutionHandler handler)
        /**
         * 拒绝策略有四种：
         * AbortPolicy()：超过最大承载量抛异常【默认】
         * CallerRunsPolicy()：哪来哪去，丢给主线程执行
         * DiscardPolicy():不抛异常，舍弃最后一个
         * DiscardOldestPolicy():不抛异常，尝试和首个进来的线程竞争资源
         *
         *
         * 最大线程怎么定义
         *  1.cpu密集型：几核心，就是几【Runtime.getRuntime().availableProcessors()】
         *  2.IO密集型：判断程序中十分占用IO的线程*2
         *      eg:15个大型线程   创建 15*2
         *
         */
        //获取cpu核数
        System.out.println(Runtime.getRuntime().availableProcessors());
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                    2,
                5,
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()//默认
        );

        try {
            //maximumPoolSize+workQueueNum=最大承载量
            for (int i = 0; i <9 ; i++) {
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
