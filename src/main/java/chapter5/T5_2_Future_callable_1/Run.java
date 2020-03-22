package chapter5.T5_2_Future_callable_1;

import Common.Tools;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 16:12
 * @Version 1.0
 *
 * 演示了
 * 1. 可以返回的类型Callable
 * 2. Future 可以返回值
 * 3. future的get方法是阻塞的
 *
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallable myCallable = new MyCallable(100);
        ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Future<String> future = pool.submit(myCallable);
        System.out.println("A "+ Tools.getTime());
        System.out.println("future="+future.get());
        System.out.println("B "+ Tools.getTime());


    }
}

class MyCallable implements Callable<String>{

    private int age;

    public MyCallable(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "返回值 年龄是："+age;
    }
}


