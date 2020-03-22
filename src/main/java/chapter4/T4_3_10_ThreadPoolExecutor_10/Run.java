package chapter4.T4_3_10_ThreadPoolExecutor_10;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 11:01
 * @Version 1.0
 *
 * getCompletedTaskCount 获取已经完成的任务
 */
public class Run {
    public static void main(String[] args) {

        Runnable runnable=()-> System.out.println("执行任务");

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

        System.out.println(pool.getCompletedTaskCount());

        Tools.sleep();
        System.out.println(pool.getCompletedTaskCount());

    }
}
