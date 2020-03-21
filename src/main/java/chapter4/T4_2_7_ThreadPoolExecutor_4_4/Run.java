package chapter4.T4_2_7_ThreadPoolExecutor_4_4;

import Common.Tools;

import java.util.concurrent.SynchronousQueue;
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
 * 案例说明： 因为没人处理多余的任务，竟然直接报错了
 *
 * 1. SynchronousQueue 队列
 * 2. 线程数量 > corePollSize
 * 3. 线程数量 > maximumPoolSize
 *
 * 如果使用了同步队列，则没有人去执行超过最大限制的线程了，则会直接报错
 *
 *
 * pool-1-thread-1 Run 1584800861539
 * pool-1-thread-6 Run 1584800861539
 * pool-1-thread-3 Run 1584800861539
 * pool-1-thread-8 Run 1584800861539
 * pool-1-thread-7 Run 1584800861539
 * pool-1-thread-4 Run 1584800861539
 * pool-1-thread-2 Run 1584800861539
 * Exception in thread "main" pool-1-thread-5 Run 1584800861539
 * java.util.concurrent.RejectedExecutionException: Task chapter4.T4_2_7_ThreadPoolExecutor_4_4.Run$1@2503dbd3 rejected from java.util.concurrent.ThreadPoolExecutor@4b67cf4d[Running, pool size = 8, active threads = 8, queued tasks = 0, completed tasks = 0]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at chapter4.T4_2_7_ThreadPoolExecutor_4_4.Run.main(Run.java:48)
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
                TimeUnit.SECONDS, new SynchronousQueue<>());

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
