package com.adouer.function;

import java.util.function.Function;

/**
 *函数型接口，输入一个自定义类型参数，返回一个自定义类型参数
 */
public class FunctionDemo {
    public static void main(String[] args) {

       /*
       //自定义内部类
       Function<String,String> function = new Function<String,String>() {
            @Override
            public String apply(String str) {
                return str;
            }
        };
        */
        //lambda表达式
        Function<String,String> function = (str)->{
            return str;
        };
        System.out.println(function.apply("123"));
    }
}
