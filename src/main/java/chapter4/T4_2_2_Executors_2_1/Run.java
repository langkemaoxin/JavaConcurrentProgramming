package chapter4.T4_2_2_Executors_2_1;

import Common.Tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 15:17
 * @Version 1.0
 *
 * 如果是连续创建的，线程池为了让任务快速完成，则会创建多个线程来处理
 * 然后处理完后，就会放到线程池中，等下一次需要用的时候，直接从线程池里面去拿
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable(i+""));
        }

        Thread.sleep(5000);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable(i+""));
        }
    }
}

class MyRunnable implements Runnable{

    private String username;

    MyRunnable(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println(Tools.getName()+" begin "+Tools.getTime());
        Tools.sleep();
        System.out.println(Tools.getName()+" end "+Tools.getTime());
    }
}

