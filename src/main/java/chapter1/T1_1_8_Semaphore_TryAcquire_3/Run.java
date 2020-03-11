package chapter1.T1_1_8_Semaphore_TryAcquire_3;

import Common.Tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 22:36
 * @Version 1.0
 *
 * 在指定的时间内,获取一个信号量
 *
 * public boolean tryAcquire(long timeout, TimeUnit unit)
 *         throws InterruptedException {
 *         return sync.tryAcquireSharedNanos(1, unit.toNanos(timeout));
 *     }
 *
 */
public
class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.setName("A");
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.setName("B");
        myThreadB.start();

    }
}

class MyService{
    private Semaphore semaphore=new Semaphore(1);
    public void testMethod(){
        try{

            if(semaphore.tryAcquire(3, TimeUnit.SECONDS)) {

                System.out.println("首次进入 ThreadName=" + Thread.currentThread().getName());
                Tools.longTask(2);
                semaphore.release(1);
                
            }else{
                System.out.println("进不去 ThreadName=" + Thread.currentThread().getName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}

class MyThreadA extends Thread{
    private MyService myService;

    MyThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

class MyThreadB extends Thread{
    private MyService myService;

    MyThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
