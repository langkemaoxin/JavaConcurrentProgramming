package chapter2.T2_2_6_CyclicBarrier_run4.T2_2_5_CyclicBarrier_run3;

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
 * 本案例研究了 getParties() 设置的关卡数量
 * getNumberWaiting() 在屏障之前等待的数量
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.start();

        MyThreadC myThreadC = new MyThreadC(myService);
        myThreadC.start();

        Thread.sleep(2000);

        System.out.println("屏障对象的parties个数为："+myService.cyclicBarrier.getParties());

        System.out.println("屏障对象的等待的个数为："+myService.cyclicBarrier.getNumberWaiting());


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

            if (Tools.getName().equals("Thread-2")) {
               Thread.sleep(Integer.MAX_VALUE);
            }

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