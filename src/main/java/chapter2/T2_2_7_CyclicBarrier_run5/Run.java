package chapter2.T2_2_7_CyclicBarrier_run5;

import Common.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/16 22:19
 * @Version 1.0
 *
 * 本案例演示了 线程在屏障之前等待的时候，如果这个时候，执行了 cyclicBarrier.reset();
 * 那么在屏障前面等待的线程就是报出异常： BrokenBarrierException
 *
 * CyclicBarrier.reset() 重置屏障，若有线程等待则报错
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.start();

        Thread.sleep(2000);

        myService.cyclicBarrier.reset();
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
            cyclicBarrier.await();
            System.out.println(Thread.currentThread().getName()+" 开始"+Tools.getTime());
        } catch (InterruptedException e) {
            System.out.println(Tools.getName() + " 进入InterruptedException e ");
        } catch (BrokenBarrierException e) {
            System.out.println(Tools.getName() + " 进入BrokenBarrierException e ");
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


class MyThreadC extends Thread {
    private MyService service;

    public MyThreadC(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}