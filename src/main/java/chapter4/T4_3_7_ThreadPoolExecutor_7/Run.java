package chapter4.T4_3_7_ThreadPoolExecutor_7;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 8:10
 * @Version 1.0
 *
 * 1. 使用SynchronousQueue
 * 2. 执行数量大于 maximumPoolSize
 *
 * 则会报错：RejectedExecutionException
 *
 * 如果想解决这问题，得设置RejectedExecutionHandler
 */
public class Run {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 999L,
                TimeUnit.SECONDS, new SynchronousQueue<>());

        RejectedExecutionHandler rejectedExecutionHandler = (r, executor) -> System.out.println("报错了哦");
        pool.setRejectedExecutionHandler(rejectedExecutionHandler);

        //独立的执行4个任务
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

        //这多出来的两个直接报错
        pool.execute(runnable);
        pool.execute(runnable);

    }
}

