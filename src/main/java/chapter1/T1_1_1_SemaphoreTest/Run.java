package chapter1.T1_1_1_SemaphoreTest;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/10 23:24
 * @Version 1.0
 *
 *
 * 这个类是用来限制并发数量的
 * public Semaphore(int permits) {
 *         sync = new NonfairSync(permits);
 * }
 *
 */
public class Run {
    public static void main(String[] args) {
        Service service = new Service();
        ThreadA threadA = new ThreadA(service);
        threadA.setName("A");

        ThreadA threadB = new ThreadA(service);
        threadB.setName("B");

        ThreadA threadC = new ThreadA(service);
        threadC.setName("C");

        threadA.start();
        threadB.start();
        threadC.start();

    }
}

class Service{
    private Semaphore semaphore=new Semaphore(2);
    public void testMethod(){
        try {

            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+" beginTime="+System.currentTimeMillis());
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()+" endTime="+System.currentTimeMillis());
            semaphore.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}


class ThreadA extends Thread{
    private Service service;

    ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}



class ThreadB extends Thread{
    private Service service;

    ThreadB(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}




class ThreadC extends Thread{
    private Service service;

    ThreadC(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}


