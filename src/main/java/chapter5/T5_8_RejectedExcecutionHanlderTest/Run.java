package chapter5.T5_8_RejectedExcecutionHanlderTest;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 22:38
 * @Version 1.0
 *
 * setRejectedExecutionHandler 当线程池关闭了之后，还强行添加任务，则会报错
 */
public class Run {
    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 2, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        pool.setRejectedExecutionHandler((a,b)->{
            System.out.println(a.toString()+"拒绝了");
        });

        pool.execute(()-> System.out.println("A"));
        pool.execute(()-> System.out.println("A"));
        pool.execute(()-> System.out.println("A"));
        pool.execute(()-> System.out.println("A"));
        pool.shutdown();

        pool.execute(()-> System.out.println("A"));
    }
}


