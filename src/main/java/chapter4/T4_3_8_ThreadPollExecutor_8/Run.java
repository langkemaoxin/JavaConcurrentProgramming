package chapter4.T4_3_8_ThreadPollExecutor_8;

import Common.Tools;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 10:25
 * @Version 1.0
 *
 * 正常的用法时，线程池在执行完后，是不会自动清空线程的
 *
 * 但是如果设置了allowCoreThreadTimeOut, 在任务执行后，在超时之后，则会把线程池中的线程清空
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 5, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        threadPoolExecutor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 4; i++) {
            MyRunnable myRunnable = new MyRunnable();
            threadPoolExecutor.execute(myRunnable);
        }

        Thread.sleep(10000);
        System.out.println(threadPoolExecutor.getPoolSize());
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行");
    }
}

