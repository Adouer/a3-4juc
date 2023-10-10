package com.adouer.unsafe;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest {
    public static void main(String[] args) {
        /**
         * 并发下Map不安全，ConcurrentModificationException【同时修改异常】
         * 解决：
         * 1. Map<String,String> map = Collections.synchronizedMap(new HashMap<>());
         * 2. Map<String,String> map = new ConcurrentHashMap<>();
         */
        Map<String,String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,5));
                System.out.println(map);
            }).start();
        }
    }
}
