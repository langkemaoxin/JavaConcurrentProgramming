package chapter2.T2_2_3_CyclicBarrier_run1;

import Common.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/15 11:30
 * @Version 1.0
 * <p>
 * 这个屏障，可以看做成一部电梯，坐满了，可以让下一批次的人做，可以控制分批次做
 */
public class Run {
    public static void main(String[] args) {

        CyclicBarrier cbRef=new CyclicBarrier(2);

        MyService myService = new MyService(cbRef);

        MyThread myThread1 = new MyThread(myService);
        myThread1.start();
        Tools.sleep();


        MyThread myThread2 = new MyThread(myService);
        myThread2.start();
        Tools.sleep();


        MyThread myThread3 = new MyThread(myService);
        myThread3.start();
        Tools.sleep();


        MyThread myThread4 = new MyThread(myService);
        myThread4.start();
        Tools.sleep();

    }
}

class MyService {
    private CyclicBarrier cbRef;

    MyService(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    public void beginRun() {
        try {

            Tools.randomThread();
            System.out.println(Tools.getName() + Tools.getTime() + " begin跑第1阶段" + (cbRef.getNumberWaiting() + 1));
            cbRef.await();
            System.out.println(Tools.getName() + Tools.getTime() + " end跑第1阶段" + (cbRef.getNumberWaiting()));


            Tools.randomThread();
            System.out.println(Tools.getName() + Tools.getTime() + " begin跑第2阶段" + (cbRef.getNumberWaiting() + 1));
            cbRef.await();
            System.out.println(Tools.getName() + Tools.getTime() + " end跑第2阶段" + (cbRef.getNumberWaiting()));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}


class MyThread extends Thread {
    public MyThread(MyService myService) {
        this.myService = myService;
    }

    private MyService myService;

    @Override
    public void run() {
        myService.beginRun();
    }
}

