package chapter4.T4_2_4_Executors_3;

import Common.Tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 16:15
 * @Version 1.0
 *
 * 指定线程数量的线程池，这样在执行操作的时候，就能限定死了，不用担心线程太多
 * ExecutorService executorService = Executors.newFixedThreadPool(3);
 *
 */
public class Run {
    public static void main(String[] args)  {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 15; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
        }

        for (int i = 0; i < 15; i++) {
            executorService.execute(new MyRunnable(i + ""));
        }

    }
}


class MyRunnable implements Runnable {

    private String username;

    MyRunnable(String username) {
        this.username = username;
    }

    @Override
    public void run() {
        System.out.println(Tools.getName() + " begin " + Tools.getTime());
        Tools.sleep();
        System.out.println(Tools.getName() + " end " + Tools.getTime());
    }
}


