package com.xunmao.demo.util;

public class HungrySingleton {

    // 2. 类的内部创建对象（需要加 final 限定，为什么？）
    private final static HungrySingleton instance = new HungrySingleton();

    // 1. 构造器私有化
    private HungrySingleton() {
    }

    // 3. 向外暴露一个静态的公共方法（一般为Get方法）
    public static HungrySingleton getInstance() {
        return instance;
    }
}
