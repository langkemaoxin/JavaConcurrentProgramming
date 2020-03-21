package chapter4.T4_2_7_ThreadPoolExecutor_3;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 17:31
 * @Version 1.0
 *
 * 案例输出结果：
 *
 * 案例说明：
 * 数量大于 corePoolSize 并且<= maximunPollSize
 *
 * 这个时候，会把其余的任务放在队列中，因为同一个时刻只有7个线程在运行
 *
 * LinkedBlockingDeque 这个类的特点是没有大小限制，队列容量非常大，所以执行excure不会爆出异常
 * 而线程中的运行线程数量也永远不会超过 corePoolSize的值
 *
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
