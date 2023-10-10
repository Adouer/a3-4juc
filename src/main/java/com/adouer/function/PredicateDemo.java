package com.adouer.function;

import java.util.function.Predicate;

/**
 * 判定型接口，输入一个自定义类型，返回boolean类型
 */
public class PredicateDemo {
    public static void main(String[] args) {

       /* //匿名内部类
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };*/
        //lambda表达式
        Predicate<String> predicate =(str)->{
            return str.isEmpty();
        };
        System.out.println(predicate.test("123"));
    }


}
