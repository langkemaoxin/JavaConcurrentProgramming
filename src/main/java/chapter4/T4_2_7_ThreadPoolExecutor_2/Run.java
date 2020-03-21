package chapter4.T4_2_7_ThreadPoolExecutor_2;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 17:12
 * @Version 1.0
 *
 * 案例输出结果：
 * pool-1-thread-1 Run 1584782330770
 * pool-1-thread-5 Run 1584782330770
 * pool-1-thread-4 Run 1584782330770
 * pool-1-thread-2 Run 1584782330770
 * pool-1-thread-6 Run 1584782330770
 * pool-1-thread-3 Run 1584782330770
 * pool-1-thread-7 Run 1584782330770
 * A:7
 * A:7
 * A:0
 * B:7
 * B:7
 * B:0
 *
 * 案例说明：
 *  执行的Runnable数量<= corePoolSize,那么马上创建线程运行这个任务，并不放入扩展队列中，其他功能参数忽略
 * Runnable x 7
 * ThreadPoolExecutor(7, 8, .....)
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

        ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        executor.execute(runnable);//1
        executor.execute(runnable);//2
        executor.execute(runnable);//3
        executor.execute(runnable);//4
        executor.execute(runnable);//5
        executor.execute(runnable);//6
        executor.execute(runnable);//7

        Thread.sleep(300);

        System.out.println("A:" + executor.getCorePoolSize());
        System.out.println("A:" + executor.getPoolSize());
        System.out.println("A:" + executor.getQueue().size());

        Thread.sleep(10000);

        System.out.println("B:" + executor.getCorePoolSize());
        System.out.println("B:" + executor.getPoolSize());
        System.out.println("B:" + executor.getQueue().size());


        System.out.println("车中可载人的标准人数:" + executor.getCorePoolSize());
        System.out.println("车中可载人的最大人数:" + executor.getMaximumPoolSize());
        System.out.println("车中正在载的人数:" + executor.getPoolSize());
        System.out.println("扩展车中正在载的人数:" + executor.getQueue().size());

    }
}
