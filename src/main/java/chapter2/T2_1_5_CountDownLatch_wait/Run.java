package chapter2.T2_1_5_CountDownLatch_wait;

import Common.Tools;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/15 11:16
 * @Version 1.0
 *
 *
 *  对象三秒后继续
 *  public boolean await(long timeout, TimeUnit unit)
 *         throws InterruptedException {
 *         return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
 *     }
 *
 */
public
class Run{
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
    private CountDownLatch count=new CountDownLatch(1);

    public void testMethod(){
        try{
            System.out.println(Tools.getName()+Tools.getTime());

            count.await(3, TimeUnit.SECONDS);

            System.out.println(Tools.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
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
