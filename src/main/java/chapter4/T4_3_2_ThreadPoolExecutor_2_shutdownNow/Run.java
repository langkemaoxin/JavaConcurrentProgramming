package chapter4.T4_3_2_ThreadPoolExecutor_2_shutdownNow;

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
 * 外界关闭了线程之后，如果想要内部及时响应，则需要使用
 * Thread.currentThread().isInterrupted() == true 来监控状态
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
        try {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                Tools.longTask();
                System.out.println("我执行完任务了"+i);
                if (Thread.currentThread().isInterrupted() == true) {
                    System.out.println("任务没有完成，就中断了");
                    throw new InterruptedException();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("进入catch中断了任务");
            e.printStackTrace();
        }
    }
}
