package chapter2.T2_2_1_CyclicBarrierThreadBigParties;

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
 * 此案例演示了两个一组，才能进行线程
 */
public class Run {
    public static void main(String[] args) {

        CyclicBarrier cbRef=new CyclicBarrier(2, new Runnable() {
            public void run() {
                System.out.println("全部都到了");
            }
        });

        Integer threadCount=5;

        MyThread[] myThreads=new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            myThreads[i]=new MyThread(cbRef);
        }
        for (int i = 0; i < threadCount; i++) {
            myThreads[i].start();
            Tools.sleep();
        }

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
            System.out.println(Tools.getName()+Tools.getTime()+"两个一组才能进行 "+(cbRef.getNumberWaiting()+1));
            cbRef.await();
            System.out.println(Tools.getName()+Tools.getTime()+"已凑齐，开始吧"+(cbRef.getNumberWaiting()+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

