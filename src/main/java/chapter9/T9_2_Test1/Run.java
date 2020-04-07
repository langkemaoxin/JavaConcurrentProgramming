package chapter9.T9_2_Test1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/7 22:08
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new MyRecursiveAction());
        Thread.sleep(5000);

    }
}

class MyRecursiveAction extends RecursiveAction{

    @Override
    protected void compute() {
        System.out.println("compute run!");
    }
}
