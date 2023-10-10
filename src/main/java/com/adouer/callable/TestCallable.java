package com.adouer.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask = new FutureTask(new MyThread());
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start(); //只输出一遍call(),证明结果有缓存
        Object o = futureTask.get(); // 获取Callable的返回，可能会发生阻塞
        System.out.println(o);

        FutureTask task = new FutureTask(new MyThread2(),"111");
        new Thread(task).start();
        System.out.println(task.get());
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("call()");
        return 1024;
    }
}

class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("MyThread2 执行");
    }
}