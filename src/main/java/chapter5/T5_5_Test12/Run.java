package chapter5.T5_5_Test12;

import Common.Tools;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 22:20
 * @Version 1.0
 *
 * Future的取消
 *       =======>future.cancel(true)
 *
 * 1. 取消完后，不能再次拿到返回的结果了
 *
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyCallble myCallble = new MyCallble();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        //线程池增加一个回调方法
        Future<String> future = threadPoolExecutor.submit(myCallble);

        Tools.sleep();
        System.out.println(future.cancel(true));
        System.out.println(future.isCancelled());

        System.out.println(future.get());
    }
}

class MyCallble implements Callable<String> {

    @Override
    public String call() throws Exception {
        try {
            int i=0;
            while (i == 0) {
                if (Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }

                System.out.println("正在运行中...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "我的年龄是2000";
    }
}
