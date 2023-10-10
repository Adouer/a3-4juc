package com.adouer.demo01;

/**
 * 卖票的例子
 * 真正公司中的开发
 * 线程是一个单独的资源类，没有附属操作
 * 1.属性、方法
 */
public class SaleTicketDemo01 {

    public static void main(String[] args) {
        //并发:多个线程造作同一个资源类（同时读写）
        Ticket ticket = new Ticket();

        //@FunctionalInterface,函数式接口,jdk1.8.Lambda表达式
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
            },"A").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"C").start();
    }


}
//资源类 oop
class Ticket {
    //属性、 方法
    private int number = 50;

    //卖票
    //synchronized 本质是排队（队列）
    public synchronized void sale() {
        if(number>0){
            System.out.println(Thread.currentThread().getName()+ "卖出了第" + number-- +"票,剩余"+number);
        }
    }

}