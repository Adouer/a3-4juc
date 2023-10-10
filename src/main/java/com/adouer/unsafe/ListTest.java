package com.adouer.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * ArrayList不安全
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         * 并发下ArrayList不安全，ConcurrentModificationException【同时修改异常】
         *  解决：
         *  1.List<String> list = new Vector<>();
         *  2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3.List<String> list = new CopyOnWriteArrayList<>();
         */

        /* List<String> list = new ArrayList<>();
           会发生java.util.ConcurrentModificationException
         */

        /*
         CopyOnWrite 写入时复制  COW 程序设计的一种优化策略 ，
         写之前复制多线程环境下最新的list，然后再写入，防止覆盖，读写分离思想
         CopyOnWriteArrayList比Vector性能高:
         CopyOnWriteArrayList使用lock
         Vector用synchronized锁方法
         */

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
          new Thread(() -> {
              list.add(UUID.randomUUID().toString().substring(0,5));
              System.out.println(list);
          },String.valueOf(i)).start();
        }
    }
}
