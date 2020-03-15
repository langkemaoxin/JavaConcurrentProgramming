package chapter2.T2_2_2_CyclicBarrier_awaitAfterReset;

import Common.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/15 11:30
 * @Version 1.0
 *
 * 这个屏障，可以看做成一部电梯，坐满了，可以让下一批次的人做，可以控制分批次做
 *
 */
public class Run {
    public static void main(String[] args) {

        CyclicBarrier cbRef=new CyclicBarrier(3);

        MyThread myThread1 = new MyThread(cbRef);
        myThread1.start();
        Tools.sleep();
        System.out.println(cbRef.getNumberWaiting());


        MyThread myThread2= new MyThread(cbRef);
        myThread2.start();
        Tools.sleep();
        System.out.println(cbRef.getNumberWaiting());


        MyThread myThread3 = new MyThread(cbRef);
        myThread3.start();
        Tools.sleep();
        System.out.println(cbRef.getNumberWaiting());


        MyThread myThread4 = new MyThread(cbRef);
        myThread4.start();
        Tools.sleep();
        System.out.println(cbRef.getNumberWaiting());

    }
}

class MyThread extends Thread{
    private CyclicBarrier cbRef;

    MyThread(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    @Override
    public void run() {
        try {
            cbRef.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

