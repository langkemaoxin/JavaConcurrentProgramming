package chapter4.T4_3_12_Policy_DiscardPolicy.T4_3_12_Policy_DiscardOldestPolicy;

import Common.Tools;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 15:04
 * @Version 1.0
 * <p>
 * 1. 原始线程数量：2
 * 2. 最大线程数量：3
 * 3. 队列容量：2
 * 4. 添加任务的拒绝策略为：DiscardPolicy
 * <p>
 * 效果为：
 * DiscardPolicy 发现队列中都已经占满后，直接丢掉。
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {

        ArrayBlockingQueue queue = new ArrayBlockingQueue<>(2);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5,
                TimeUnit.SECONDS, queue,
                new ThreadPoolExecutor.DiscardPolicy());

        for (int i = 0; i < 5; i++) {
            MyRunnable myRunnable = new MyRunnable("Runnable " + (i + 1));
            pool.execute(myRunnable);
        }

        Thread.sleep(50);
        Iterator iterator = queue.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println("队列中："+((MyRunnable)next).getName());
        }


        pool.execute( new MyRunnable("Runnable6"));
        //pool.execute( new MyRunnable("Runnable7"));

        System.out.println("添加了额外的两个任务之后");

        iterator = queue.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println("队列中："+((MyRunnable)next).getName());
        }

    }
}

class MyRunnable implements Runnable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " Run");
        Tools.sleep();
    }
}
