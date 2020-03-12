package chapter1.T1_1_13_Semaphore_MoreToOne_2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 8:27
 * @Version 1.0
 *
 * semaphore.acquire(); 取得资格
 * ReentrantLock.lock() 只允许一个线程获取，其他线程阻塞
 *
 *
 */
public class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();

        Integer threadCount=5;

        MyThread[] myThreads=new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            myThreads[i]=new MyThread(myService);
        }
        for (int i = 0; i < threadCount; i++) {
            myThreads[i].start();
        }

    }
}

class MyService{
    private Semaphore semaphore=new Semaphore(3);
    private ReentrantLock lock=new ReentrantLock();

    public void testMethod(){
        try{
            semaphore.acquire();
            System.out.println(Thread.currentThread().getName()+"准备");

            lock.lock();

            System.out.println(Thread.currentThread().getName()+"begin hello "+System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"打印"+(i+1));
            }
            System.out.println(Thread.currentThread().getName()+"end hello "+System.currentTimeMillis());

            lock.unlock();

            semaphore.release();

            System.out.println( Thread.currentThread().getName()+"结束");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyThread extends Thread{
    private MyService myService;

    MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
