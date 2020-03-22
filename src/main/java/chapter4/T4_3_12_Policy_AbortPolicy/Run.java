package chapter4.T4_3_12_Policy_AbortPolicy;

import Common.Tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 15:04
 * @Version 1.0
 *
 * 1. 原始线程数量：2
 * 2. 最大线程数量：3
 * 3. 队列容量：2
 * 4. 添加任务的拒绝策略为：AbortPolicy
 *
 * 效果为：
 * 可以同时执行5个任务【最大线程数量3+队列数量2】
 *
 */
public class Run {
    public static void main(String[] args) {
        Runnable runnable=()-> System.out.println("执行"+ Tools.getName());

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2),
                new ThreadPoolExecutor.AbortPolicy());

        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

        //但是这里不可以执行，一旦执行就会报错,因为策略是 直接拒绝
        pool.execute(runnable);


    }
}
