package chapter4.T4_2_6_Executors_4;

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
 * Executors.newSingleThreadExecutor();
 *
 */
public class Run {
    public static void main(String[] args)  {

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 15; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                }
            });
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


