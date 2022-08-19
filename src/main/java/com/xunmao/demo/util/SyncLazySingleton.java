package com.xunmao.demo.util;

public class SyncLazySingleton {

    // 2. 类的内部创建对象
    private static SyncLazySingleton instance;

    // 1. 构造器私有化
    private SyncLazySingleton() {
        System.out.println(Thread.currentThread().getName() + ": OK");
    }

    // 3. 向外暴露一个静态的公共方法（添加 synchronized 同步处理）
    public static synchronized SyncLazySingleton getInstance() {
        if (instance == null) {
            instance = new SyncLazySingleton();
        }
        return instance;
    }
}
