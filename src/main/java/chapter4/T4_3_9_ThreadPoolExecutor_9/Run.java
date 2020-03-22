package chapter4.T4_3_9_ThreadPoolExecutor_9;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 11:01
 * @Version 1.0
 *
 * prestartCoreThread 提前启动一个核心线程，虽然不做事
 *
 * prestartAllCoreThreads 提前创建所有的核心线程
 */
public class Run {
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        System.out.println(pool.getPoolSize());

        pool.prestartCoreThread();
        System.out.println(pool.getPoolSize());

        pool.prestartCoreThread();
        System.out.println(pool.getPoolSize());
    }
}


class Run1 {
    public static void main(String[] args) {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        System.out.println(pool.getPoolSize());

        pool.prestartAllCoreThreads();
        System.out.println(pool.getPoolSize());

        pool.prestartCoreThread();
        System.out.println(pool.getPoolSize());
    }
}
