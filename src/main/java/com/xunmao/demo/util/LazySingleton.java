package com.xunmao.demo.util;

public class LazySingleton {

    // 2. 类的内部创建对象
    private static LazySingleton instance;

    // 1. 构造器私有化
    private LazySingleton() {
        System.out.println(Thread.currentThread().getName() + ": OK");
    }

    // 3. 向外暴露一个静态的公共方法（一般为Get方法）
    public static LazySingleton getInstance() {
        // 返回对象之前判断对象是否初始化
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
