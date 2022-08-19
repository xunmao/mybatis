package com.xunmao.demo.util;

public class DclLazySingleton {

    // 2. 类的内部创建对象（注意 volatile 限定，防止指令重排）
    private static volatile DclLazySingleton instance;

    // 1. 构造器私有化
    private DclLazySingleton() {
        System.out.println(Thread.currentThread().getName() + ": OK");
    }

    // 3. 向外暴露一个静态的公共方法
    public static DclLazySingleton getInstance() {
        // 检查实例是否为空
        // 1. 实例为空代表尚未完成初始化
        if (instance == null) {
            // 1.1. 使用同步锁，防止并发影响实例初始化
            synchronized (DclLazySingleton.class) {
                // 1.2. 再次确认实例是否初始化，检测加锁过程中其他线程是否初始化实例
                if (instance == null) {
                    instance = new DclLazySingleton();
                    // new DclLazySingleton(); 操作不是原子性的操作
                    // 上述操作包含以下三步：
                    // 1. 分配内存空间
                    // 2. 初始化对象
                    // 3. 设置 instance 指向刚刚分配的内存地址
                    // 其中的2、3步可能发生指令重排：
                    // 1. 分配内存空间
                    // 2. 设置 instance 指向刚刚分配的内存地址
                    // 3. 初始化对象
                }
            }
        }
        // 2. 实例不为空代表已经完成初始化，可以直接返回实例
        return instance;
    }
}
