package chapter4.T4_2_2_Executors_2;

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
 *
 * pool-1-thread-4 begin 1584775278110
 * pool-1-thread-7 begin 1584775278110
 * pool-1-thread-3 begin 1584775278110
 * pool-1-thread-1 begin 1584775278110
 * pool-1-thread-5 begin 1584775278110
 * pool-1-thread-8 begin 1584775278110
 * pool-1-thread-2 begin 1584775278110
 * pool-1-thread-10 begin 1584775278110
 * pool-1-thread-9 begin 1584775278110
 * pool-1-thread-6 begin 1584775278110
 * pool-1-thread-5 end 1584775280111
 * pool-1-thread-2 end 1584775280111
 * pool-1-thread-6 end 1584775280111
 * pool-1-thread-4 end 1584775280111
 * pool-1-thread-1 end 1584775280111
 * pool-1-thread-7 end 1584775280111
 * pool-1-thread-8 end 1584775280111
 * pool-1-thread-9 end 1584775280111
 * pool-1-thread-3 end 1584775280111
 * pool-1-thread-10 end 1584775280111
 *
 *
 * 自己直接运行 ExecutorService 的话，是达不到 池中线程对象可以复用的效果
 *
 */
public class Run {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
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

