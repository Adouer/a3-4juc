package com.adouer.function;

import java.util.function.Consumer;

/**
 * 消费型接口,输入自定义类型没有返回值
 */
public class ConsumerDemo {
    public static void main(String[] args) {

        /*//匿名内部类
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };*/
        //lambda表达式
        Consumer<String> consumer = (str)->{
            System.out.println(str);
        };
        consumer.accept("123");

    }
}
