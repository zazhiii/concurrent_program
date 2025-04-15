package com.zazhi.p01_create_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zazhi
 * @date 2025/4/13
 * @description: 线程创建
 */
@Slf4j
public class Test1 {

    public static void main(String[] args) {
        Test1 test1 = new Test1();
//        test1.f1();
//        test1.f2();
        test1.f3();
    }
    // 方式1：直接使用 Thread 类
    public void f1(){
        Thread t1 = new Thread(() -> {
            log.debug("running");
        }, "t1" ); // 线程名称
        t1.start();
        log.debug("running");
    }

    // 方式2：Runnable 配合 Thread
    public void f2(){
        Runnable runnable = () -> {
            log.debug("running");
        };
        Thread t1 = new Thread(runnable, "t1" ); // 线程名称
        t1.start();
        log.debug("running");
    }

    // 方式3
    public void f3(){

        // 泛型定义返回值类型
        Callable callable = () -> {
            log.debug("经过漫长的计算。。。");
            Thread.sleep(1000);
            return 1 + 1;
        };

        FutureTask<Integer> futureTask = new FutureTask(callable);

        Thread t1 = new Thread(futureTask, "t1" ); // 线程名称

        t1.start();

        log.debug("main is running");

        try {
            log.debug("结果: {}", futureTask.get());
        } catch (Exception e) {
            throw new RuntimeException("计算异常", e);
        }
    }
}
