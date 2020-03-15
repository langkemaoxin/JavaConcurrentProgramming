package chapter2.T2_1_1_CountDownLatch_test1;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/14 18:14
 * @Version 1.0
 *
 * CountDownLatch
 *
 * 本案例演示了 当计数器为0时，线程才被唤醒
 *
 * 是一个很有用的类
 */
public
class Run{
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();

        Thread.sleep(2000);

        myService.downMethod();

    }
}

class MyService{

    private CountDownLatch down=new CountDownLatch(1);

    public void testMethod(){
        try{

            System.out.println("A");
            down.await();
            System.out.println("B");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
    }

    public void downMethod(){
        System.out.println("X");
        down.countDown();
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

