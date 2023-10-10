package com.adouer.unsafe;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {
        /**
         * 并发下 set不安全，ConcurrentModificationException【同时修改异常】
         *  解决
         *  1.Set<String> set = Collections.synchronizedSet(new HashSet<>());
         *  2.Set<String> set = new CopyOnWriteArraySet<>();
         */


        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0,5));
                System.out.println(set);
            },String.valueOf(i)).start();
        }



    }
}
