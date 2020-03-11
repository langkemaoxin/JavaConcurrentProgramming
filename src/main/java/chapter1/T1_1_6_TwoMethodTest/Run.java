package chapter1.T1_1_6_TwoMethodTest;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 21:53
 * @Version 1.0
 *
 *
 * 大约还有5个线程在等待
 * 是否有线程正在等待信号量？true
 * 大约还有4个线程在等待
 * 是否有线程正在等待信号量？true
 * 大约还有3个线程在等待
 * 是否有线程正在等待信号量？true
 * 大约还有2个线程在等待
 * 是否有线程正在等待信号量？true
 * 大约还有1个线程在等待
 * 是否有线程正在等待信号量？true
 * 大约还有0个线程在等待
 * 是否有线程正在等待信号量？false
 *
 *
 *
 * 这只是用来估算线程等待数量的值，只做参考，因为他是动态变化的
 *
 *      * Returns an estimate of the number of threads waiting to acquire.
 *      * The value is only an estimate because the number of threads may
 *      * change dynamically while this method traverses internal data
 *      * structures.  This method is designed for use in monitoring of the
 *      * system state, not for synchronization control.
 *
 *  public final int getQueueLength() {
 *         return sync.getQueueLength();
 *  }
 *
 */
public class Run {
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();


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
    private Semaphore semaphore=new Semaphore(1);
    public void testMethod(){
        try{

            semaphore.acquire();
            Thread.sleep(1000);
            System.out.println("大约还有"+semaphore.getQueueLength()+"个线程在等待");
            System.out.println("是否有线程正在等待信号量？"+semaphore.hasQueuedThreads());

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
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
