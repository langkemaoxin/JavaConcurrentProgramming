package chapter4.T4_3_12_Policy_CallerRunsPolicy_1;

import Common.Tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
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
 * 4. 添加任务的拒绝策略为：CallerRunsPolicy
 *
 * 效果为：
 * 可以同时执行5个任务【最大线程数量3+队列数量2】
 * 在执行第6个任务时，由阻塞线程新建，然后执行
 *
 */
public class Run {
    public static void main(String[] args) {
        Runnable runnable=()-> {System.out.println("执行"+ Tools.getName());Tools.sleep();};

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());

        System.out.println("A begin");
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);


        //由main线程创建任务，阻塞执行！！！
        pool.execute(runnable);
        pool.execute(runnable);

        System.out.println("A End");




    }
}
