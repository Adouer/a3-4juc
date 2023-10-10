package com.adouer.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * 题目要求：用一行代码
 * 现在有5个用户！筛选：
 * 1.id必须为偶数
 * 2.年龄大于23
 * 3.用户名转换为大写字母
 * 4.用户名字母倒着排序
 * 5.只输出一个用户
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(5, "e", 25);
        User u6 = new User(6, "e", 26);

        //集合存储
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5,u6);

        //流计算
        list.stream()
                .filter(u -> u.getId()%2==0)
                .filter(u -> u.getAge()>23)
                .map(u -> {
                    u.setName(u.getName().toUpperCase());
                    return u;
                })
                .sorted((o1, o2) -> {return o2.getName().compareTo(o1.getName());})
                .forEach(System.out::println);
    }
}
