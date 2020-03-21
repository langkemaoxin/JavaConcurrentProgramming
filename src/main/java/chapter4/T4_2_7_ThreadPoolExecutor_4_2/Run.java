package chapter4.T4_2_7_ThreadPoolExecutor_4_2;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 22:19
 * @Version 1.0
 *
 * 案例输出结果：
 *
 * 案例说明：
 * 数量大于 > maximunPollSize
 *
 * 如果执行数量大于 核心数量 && 执行数量 > 最大数量 &&LilnkBlock队列，
 * 多的数量放入 队列中
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Run " + Tools.getTime());
                Tools.sleep();
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 5,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        executor.execute(runnable);//1
        executor.execute(runnable);//2
        executor.execute(runnable);//3
        executor.execute(runnable);//4
        executor.execute(runnable);//5
        executor.execute(runnable);//6
        executor.execute(runnable);//7
        executor.execute(runnable);//8
        executor.execute(runnable);//9

        Thread.sleep(300);

        System.out.println("A:" + executor.getCorePoolSize());
        System.out.println("A:" + executor.getPoolSize());
        System.out.println("A:" + executor.getQueue().size());

        Thread.sleep(10000);

        System.out.println("B:" + executor.getCorePoolSize());
        System.out.println("B:" + executor.getPoolSize());
        System.out.println("B:" + executor.getQueue().size());



    }
}
