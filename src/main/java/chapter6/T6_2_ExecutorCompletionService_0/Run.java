package chapter6.T6_2_ExecutorCompletionService_0;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 23:18
 * @Version 1.0
 *
 * 1. 新建一个线程池
 * 2. 把线程池丢给ExecutorCompletionService
 * 3. 把所有的任务用 ExecutorCompletionService提交
 * 4. 阻塞性的获取返回结果，【返回结果都是乱序的，都是同时进行的】
 *
 * 获取一个Future对象
 * ExecutorCompletionService.take().get()
 *
 *
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable userName1 = new MyCallable("userName1", 5000);
        MyCallable userName2 = new MyCallable("userName2", 5000);
        MyCallable userName3 = new MyCallable("userName3", 5000);
        MyCallable userName4 = new MyCallable("userName4", 5000);
        MyCallable userName5 = new MyCallable("userName5", 5000);

        ArrayList<Callable> callables = new ArrayList<>();
        callables.add(userName1);
        callables.add(userName2);
        callables.add(userName3);
        callables.add(userName4);
        callables.add(userName5);

        ArrayList<Future> futures = new ArrayList<>();

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        //把线程池对象丢给
        //把线程池对象丢给 ExecutorCompletionService
        ExecutorCompletionService csRef = new ExecutorCompletionService(poolExecutor);

        for (int i = 0; i < 5; i++) {
            //然后把callable任务 提交了
            csRef.submit(callables.get(i));
        }

        System.out.println("run first time=" + System.currentTimeMillis());

        for (int i = 0; i < 6; i++) {
            System.out.println("正在打印"+i+"个");
            System.out.println(csRef.take().get());
        }

        System.out.println("全部结束了");

    }
}

class MyCallable implements Callable<String> {

    private String username;
    private long sleepValue;

    MyCallable(String username, long sleepValue) {
        this.username = username;
        this.sleepValue = sleepValue;
    }

    @Override
    public String call() throws Exception {
        System.out.println(username);
        Thread.sleep(sleepValue);
        return "return " + username+System.currentTimeMillis();
    }
}
