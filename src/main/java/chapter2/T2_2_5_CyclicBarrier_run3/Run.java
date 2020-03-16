package chapter2.T2_2_5_CyclicBarrier_run3;

import Common.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/16 22:19
 * @Version 1.0
 *
 * 一荣俱荣，一损俱损。
 * 要么同时乖乖的在屏障前面等待。
 *
 * 倘若一个线程使用了等待几秒后超时了，这个线程本身会抛出 TimeoutException
 * 而其他的同类线程，则会抛出 BrokenBarrierException,屏障破坏
 *
 * 关卡，障碍
 * 演示了 CyclicBarrier 的等待超时 函数public int await(long timeout, TimeUnit unit)
 *
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.start();


        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.start();


    }
}

class MyService {
    public CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        public void run() {
            System.out.println("彻底结束了 " + Tools.getTime());
        }
    });

    public void testMethod() {
        try {
            System.out.println(Tools.getName() + " 准备！" + Tools.getTime());

            if (Tools.getName().equals("Thread-0")) {
                System.out.println("Thread-0 执行了 await5");
                cyclicBarrier.await(5, TimeUnit.SECONDS);
            }

            if (Tools.getName().equals("Thread-1")) {
                System.out.println("Thread-1 执行了 await()");
                cyclicBarrier.await();
            }

        } catch (InterruptedException e) {
            System.out.println(Tools.getName() + " 进入InterruptedException e ");
        } catch (BrokenBarrierException e) {
            System.out.println(Tools.getName() + " 进入BrokenBarrierException e ");
        } catch (TimeoutException e) {
            System.out.println(Tools.getName() + " 进入TimeoutException e ");
        }
    }
}

class MyThreadA extends Thread {
    private MyService service;

    public MyThreadA(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class MyThreadB extends Thread {
    private MyService service;

    public MyThreadB(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}