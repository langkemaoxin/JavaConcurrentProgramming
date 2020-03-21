package chapter4.T4_3_5_awaitTermination;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 23:36
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName());

        //创建一个线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 9999, 999L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        //独立的执行4个任务
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);
        pool.execute(runnable);

        //通知线程池关闭
        pool.shutdown();

        //等待线程池中的线程全部关闭
        pool.awaitTermination(Integer.MAX_VALUE,TimeUnit.SECONDS);

        System.out.println("全部任务执行了");
    }
}
