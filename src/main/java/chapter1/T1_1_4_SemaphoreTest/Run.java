package chapter1.T1_1_4_SemaphoreTest;

import Common.Tools;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 7:41
 * @Version 1.0
 *
 *
 * <p>If the current thread is interrupted
 * while waiting for a permit then it will continue to wait, but the
 * time at which the thread is assigned a permit may change compared to
 * the time it would have received the permit had no interruption
 * occurred.  When the thread does return from this method its interrupt
 * status will be set.
 *
 * public void acquireUninterruptibly() {
 *         sync.acquireShared(1);
 * }
 *
 * 当在等待许可的情况下，发生了中断情况，线程不会直接中断
 * 当线程离开了等待许可的范围时，才会被设置为中断状态
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");

        ThreadB threadB = new ThreadB(service);
        threadB.setName("B");

        ThreadC threadC = new ThreadC(service);
        threadC.setName("C");

        threadA.start();
        threadB.start();
        threadC.start();

        Tools.longTask();

        threadC.interrupt();


        System.out.println("Main线程中判断： "+threadC.getName()+"等待许可之后 是否被中断："+threadC.isInterrupted());
        System.out.println("Main线程中判断： "+threadC.getName()+"等待许可之后 是否被中断："+threadC.isInterrupted());
        System.out.println("Main线程中判断： "+threadC.getName()+"等待许可之后 是否被中断："+threadC.isInterrupted());
    }
}

class Service {
    private Semaphore semaphore = new Semaphore(2);

    public void testMethod() {

        //####
        System.out.println(Thread.currentThread().getName()+"等待许可之前 是否被中断："+Thread.currentThread().isInterrupted());

        semaphore.acquireUninterruptibly();

        //####
        System.out.println(Thread.currentThread().getName()+"等待许可之间 是否被中断："+Thread.currentThread().isInterrupted());

        //System.out.println(Thread.currentThread().getName() + " beginTime=" + System.currentTimeMillis());
        Tools.longTask(3);
        //System.out.println(Thread.currentThread().getName() + " endTime=" + System.currentTimeMillis());

        semaphore.release();

        //####
        System.out.println(Thread.currentThread().getName()+"等待许可之后 是否被中断："+Thread.currentThread().isInterrupted());
    }
}


class ThreadA extends Thread {
    private Service service;

    ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}


class ThreadB extends Thread {
    private Service service;

    ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}


class ThreadC extends Thread {
    private Service service;

    ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}


