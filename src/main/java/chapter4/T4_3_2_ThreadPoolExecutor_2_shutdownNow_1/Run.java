package chapter4.T4_3_2_ThreadPoolExecutor_2_shutdownNow_1;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 22:56
 * @Version 1.0
 *
 * 1. 创建一个线程池后
 * 2. 给池中添加任务
 * 3. 然后马上把线程池关闭
 * 4. 但是线程池发现正在执行任务，这个时候不会马上就中断线程，而是等到这个任务执行完后，才关闭线程池
 *
 */
public class Run {
    public static void main(String[] args) {
        MyRunnable1 myRunnable1 = new MyRunnable1();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 9999,
                999L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        pool.execute(myRunnable1);
        pool.execute(myRunnable1);
        pool.execute(myRunnable1);
        pool.execute(myRunnable1);

        System.out.println("执行shutdownNow");
        pool.shutdownNow();
    }
}

class MyRunnable1 implements Runnable {
    @Override
    public void run() {

        Tools.longTask();

        System.out.println("我执行完任务了");

    }
}
