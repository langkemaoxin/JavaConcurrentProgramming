package chapter4.T4_3_2_ThreadPoolExecutor_2;

import Common.Tools;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class MyRunnable implements Runnable {

    @Override
    public void run() {
        Tools.pBegin();
        Tools.sleep();
        Tools.pEnd();
    }
}

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 22:37
 * @Version 1.0
 * 因为线程池中没有任务，则直接退出了
 */
public class Run {
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(7, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        System.out.println("end");
    }
}


/**
 * 线程池的常态：
 * 执行完任务后，回一直保留，等待下一个任务
 *
 */
class Test2{
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(7, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        threadPoolExecutor.execute(myRunnable);
        System.out.println("end");
    }
}

/**
 * 任务执行完成后进程结束
 *  pool.shutdown()
 */
class Test3{
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(7, 10, 0L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        pool.execute(myRunnable);
        pool.shutdown();
        System.out.println("end");
    }
}


/**
 * pool.shutdown();之后，线程池销毁
 * 这个时候再执行  pool.execute(myRunnable); 则会报错
 *
 * [Shutting down, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 2]
 *
 */
class Test4{
    public static void main(String[] args) {
        MyRunnable myRunnable = new MyRunnable();
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 9999, 9999L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        pool.execute(myRunnable);
        pool.execute(myRunnable);
        pool.execute(myRunnable);
        pool.execute(myRunnable);
        Tools.sleep();

        pool.shutdown();

        pool.execute(myRunnable);

        System.out.println("end");
    }
}