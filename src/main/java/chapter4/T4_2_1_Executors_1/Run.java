package chapter4.T4_2_1_Executors_1;

import Common.Tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 15:08
 * @Version 1.0
 *
 * Executors.newCachedThreadPool(); 创建一个无界线程池，可以进行线程自动回收
 *
 */
public class Run {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable1 begin " + Tools.getTime());
                Tools.sleep();
                System.out.println("A");
                System.out.println("Runnable1 end " + Tools.getTime());
            }
        });

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable2 begin " + Tools.getTime());
                Tools.sleep();
                System.out.println("B");
                System.out.println("Runnable2 end " + Tools.getTime());
            }
        });
    }
}


class Run2 {
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Runnable1 begin " + Tools.getTime());
                    System.out.println("A");
                    System.out.println("Runnable1 end " + Tools.getTime());
                }
            });
        }
    }
}
