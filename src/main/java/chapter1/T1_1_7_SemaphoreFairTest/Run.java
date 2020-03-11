package chapter1.T1_1_7_SemaphoreFairTest;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 22:19
 * @Version 1.0
 *
 *
 * 不公平：
 * ThreadName=Thread-0启动了
 * ThreadName=Thread-4启动了
 * ThreadName=Thread-0
 * ThreadName=Thread-2启动了
 * ThreadName=Thread-1启动了
 * ThreadName=Thread-3启动了
 * ThreadName=Thread-4
 * ThreadName=Thread-2
 * ThreadName=Thread-1
 * ThreadName=Thread-3
 *
 * 公平（不能保证每次都这样，只是几率大一些）
 * ThreadName=Thread-0启动了
 * ThreadName=Thread-3启动了
 * ThreadName=Thread-4启动了
 * ThreadName=Thread-2启动了
 * ThreadName=Thread-1启动了
 * ThreadName=Thread-0
 * ThreadName=Thread-3
 * ThreadName=Thread-4
 * ThreadName=Thread-2
 * ThreadName=Thread-1
 *
 *
 *      如果构造函数传入公平锁后，底层只是尽可能的保证
 *
 *      if this semaphore will guarantee
 *      first-in first-out granting of permits under contention
 *
 *      public Semaphore(int permits,boolean fair){
 *          sync=fair?new FairSync(permits):new NonfairSync(permits);
 *      }
 *
 */
public class Run {
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

class MyService {
    private boolean isFair=true;

    private Semaphore semaphore = new Semaphore(1,isFair);

    public void testMethod() {
        try {
            semaphore.acquire();
            System.out.println("ThreadName=" + Thread.currentThread().getName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}

class MyThread extends Thread {
    private MyService myService;

    MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        System.out.println("ThreadName=" + Thread.currentThread().getName()+"启动了");
        myService.testMethod();
    }
}
