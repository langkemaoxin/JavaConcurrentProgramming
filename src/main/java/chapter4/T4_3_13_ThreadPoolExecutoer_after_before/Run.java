package chapter4.T4_3_13_ThreadPoolExecutoer_after_before;

import Common.Tools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 15:52
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyThreadPoolExecutor myThreadPoolExecutor = new MyThreadPoolExecutor(2, 3, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        myThreadPoolExecutor.execute(new MyRunnable("A"));
        myThreadPoolExecutor.execute(new MyRunnable("B"));
        myThreadPoolExecutor.execute(new MyRunnable("C"));
        myThreadPoolExecutor.execute(new MyRunnable("D"));

        Thread.sleep(10000);

        System.out.println(myThreadPoolExecutor.getTaskCount());
    }
}

class MyThreadPoolExecutor extends ThreadPoolExecutor{

    public MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        System.out.println(((MyRunnable)r).getName()+" 执行完了");
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(((MyRunnable)r).getName()+" 准备开始执行了");
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
