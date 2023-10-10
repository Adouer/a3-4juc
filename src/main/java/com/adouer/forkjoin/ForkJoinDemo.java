package com.adouer.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * ForkJoin分支合并
 *  步骤：
 *      1.继承递归任务
 *      2.重写compute方法
 *          （1）设定临界值，根据临界值确定采取的方法
 *          （2）对数据量较大的部分采用分支合并
 *              ·确定中间值
 *              ·构造方法创建分支任务
 *              ·fork进行拆分
 *              ·join进行结果整合
 */
public class ForkJoinDemo extends RecursiveTask<Long> {

    private Long start;
    private Long end;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    //临界值
    private Long temp = 10000L;
    @Override
    protected Long compute() {

        if((end-start)<temp){
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        }else {
            //分支合并
            long mid = (end + start) / 2;   //求中间值
            ForkJoinDemo task1 = new ForkJoinDemo(start, mid);
            task1.fork();   //拆分任务，把任务压入线程队列
            ForkJoinDemo task2 = new ForkJoinDemo(mid+1, end);
            task2.fork();   //拆分任务，把任务压入线程队列
            return task1.join()+task2.join();
        }
    }
}
