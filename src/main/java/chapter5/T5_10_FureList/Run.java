package chapter5.T5_10_FureList;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 23:02
 * @Version 1.0
 *
 * Future的默认实现类 是 FutureTask
 * 这个类有一个缺点，那就是在进行取值的时候是，阻塞型的
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

        for (int i = 0; i < 5; i++) {
            futures.add(poolExecutor.submit(callables.get(i)));
        }

        System.out.println("run first time="+System.currentTimeMillis());

        for (int i = 0; i < 5; i++) {
            System.out.println(futures.get(i).get()+" "+System.currentTimeMillis());
        }

    }
}

class MyCallable implements Callable<String>{

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
        return "return "+username;
    }
}
