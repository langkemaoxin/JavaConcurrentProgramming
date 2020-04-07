package chapter9.T9_4_Test1;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/7 22:08
 * @Version 1.0
 *
 * Join和 get方法有什么区别
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyRecursiveTask task1 = new MyRecursiveTask();

        System.out.println(task1.hashCode());

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask task2 = pool.submit(task1);

        ForkJoinTask<Integer> submit = pool.submit(task1);

        System.out.println(task2.hashCode() + "  " + task2.get());

        Thread.sleep(2000);

    }
}

class Run2 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        MyRecursiveTask task1 = new MyRecursiveTask();

        System.out.println(task1.hashCode());

        ForkJoinPool pool = new ForkJoinPool();

        ForkJoinTask task2 = pool.submit(task1);


        System.out.println(task2.hashCode() + "  " + task2.join());

        Thread.sleep(2000);

    }
}

class MyRecursiveTask extends RecursiveTask<Integer> {

    @Override
    protected Integer compute() {
        return 100;
    }
}
