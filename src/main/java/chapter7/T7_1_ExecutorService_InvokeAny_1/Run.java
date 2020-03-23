package chapter7.T7_1_ExecutorService_InvokeAny_1;

import Common.Tools;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/23 22:47
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List list=new ArrayList();
        list.add(new MyCallableA());
        list.add(new MyCallableB1());

        ExecutorService executorService = Executors.newCachedThreadPool();
        Object value = executorService.invokeAny(list);

        System.out.println("==============="+value);
        System.out.println("zzzzzzzzzzzzzzzz");
    }
}


/**
 * 线程A执行完返回后，设置线程B的状态为中断状态
 *
 * 方法InvokeAny()取得第一个完成任务的结果值，当第一个任务完成后，
 * 调用interrupt()方法将其他任务中断
 */
class Run2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List list=new ArrayList();
        list.add(new MyCallableA());
        list.add(new MyCallableB2());

        ExecutorService executorService = Executors.newCachedThreadPool();
        Object value = executorService.invokeAny(list);

        System.out.println("==============="+value);
        System.out.println("zzzzzzzzzzzzzzzz");
    }
}

class MyCallableA implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableA begin "+System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            Tools.longTask();
            System.out.println("MyCallableA "+(i+1));
        }
        System.out.println("MyCallableA end "+System.currentTimeMillis());
        return "returnA";
    }
}


class MyCallableB1 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB begin "+System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            Tools.longTask();
            System.out.println("MyCallableB "+(i+1));
        }
        System.out.println("MyCallableB end "+System.currentTimeMillis());
        return "returnB";
    }
}

class MyCallableB2 implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("MyCallableB begin "+System.currentTimeMillis());
        for (int i = 0; i < 1000; i++) {
            if(Thread.currentThread().isInterrupted()==false){
                Tools.longTask(2);
                System.out.println("MyCallableB "+(i+1));
            }else{
                System.out.println("*****************爆出异常");
                throw new InterruptedException();
            }
        }
        System.out.println("MyCallableB end "+System.currentTimeMillis());
        return "returnB";
    }
}

