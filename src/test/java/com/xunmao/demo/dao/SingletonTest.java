package com.xunmao.demo.dao;

import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.xunmao.demo.util.DclLazySingleton;
import com.xunmao.demo.util.HungrySingleton;
import com.xunmao.demo.util.LazySingleton;
import com.xunmao.demo.util.SyncLazySingleton;

public class SingletonTest {

    @Test
    public void shouldGetHungrySingleton() {
        HungrySingleton instance1 = HungrySingleton.getInstance();
        HungrySingleton instance2 = HungrySingleton.getInstance();

        assertSame(instance1, instance2);

        System.out.println("instance1.hashCode: " + instance1.hashCode());
        System.out.println("instance2.hashCode: " + instance2.hashCode());
    }

    @Test
    public void shouldGetLazySingleton() {
        LazySingleton instance1 = LazySingleton.getInstance();
        LazySingleton instance2 = LazySingleton.getInstance();

        assertSame(instance1, instance2);

        System.out.println("instance1.hashCode: " + instance1.hashCode());
        System.out.println("instance2.hashCode: " + instance2.hashCode());
    }

    @Test
    public void mayNotGetLazySingleton() {
        // 开启多个线程，同时获取对象
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton.getInstance();
            }).start();
        }
    }

    @Test
    public void shouldGetSyncLazySingleton() {
        // 开启多个线程，同时获取对象
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                SyncLazySingleton.getInstance();
            }).start();
        }
    }

    @Test
    public void shouldGetDclLazySingleton() {
        // 开启多个线程，同时获取对象
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DclLazySingleton.getInstance();
            }).start();
        }
    }
}
