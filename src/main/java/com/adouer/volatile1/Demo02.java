package com.adouer.volatile1;

/**
 * volatile不保证原子性，加不加volatile都一样
 */
public class Demo02 {

    //volatile不保证原子性
    private volatile static int num = 0;

    public static void add() {
        num++;
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
