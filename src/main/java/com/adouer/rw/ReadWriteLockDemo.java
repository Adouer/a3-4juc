package com.adouer.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 1; i <=5 ; i++) {
            int temp = i;
            new Thread(() -> {
                myCacheLock.put(temp+"",temp);
            },String.valueOf(i)).start();
        }
        for (int i = 1; i <=5 ; i++) {
            int temp = i;
            new Thread(() -> {
                myCacheLock.get(temp+"");
            },String.valueOf(i)).start();
        }
    }

}

/**
 * 资源类【属性、方法】
 */
class MyCacheLock {

    private volatile Map<String,Object> map = new HashMap<>();
    //创建读写锁
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();


    //写入
    public void put(String key ,Object value){
        try {
            //获取锁
            readWriteLock.writeLock().lock();
            /*业务*/
            System.out.println(Thread.currentThread().getName()+"写入："+ key);
            TimeUnit.SECONDS.sleep(10);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放锁
            readWriteLock.writeLock().unlock();
        }
    }

    //读取
    public Object get(String key){
        try {
            //获取锁
            readWriteLock.readLock().lock();
            /*业务*/
            System.out.println(Thread.currentThread().getName()+"读取："+ key);
            Object o = map.get(key);
            System.out.println("值为："+o);
            System.out.println(Thread.currentThread().getName()+"读取结束");
            return o;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return null;
    }
}