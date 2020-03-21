package chapter4.T4_2_3_newCachedThreadPoolFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 16:04
 * @Version 1.0
 *
 * 定制线程池
 */
public class Run {
    public static void main(String[] args) {

        MyThreadFactory myThreadFactory = new MyThreadFactory();
        ExecutorService executorService = Executors.newCachedThreadPool(myThreadFactory);
        for (int i = 0; i < 100; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("我在运行在 【" + Thread.currentThread().getName() + "】");

                }
            });

        }


    }
}

class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {

        System.out.println("创建了一个新的线程");
        Thread thread = new Thread(r);
        thread.setName("定制线程3800");
        return thread;
    }
}
