package chapter2.T2_2_1_CyclicBarrierBegin;

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
 * 5个线程都执行了cbRef的await方法，程序才能向下执行
 */
public class Run {
    public static void main(String[] args) {

        CyclicBarrier cbRef=new CyclicBarrier(5, new Runnable() {
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
        }

        System.out.println("main end");
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
            Tools.randomThread();
            System.out.println(Tools.getName()+Tools.getTime()+" "+cbRef.getNumberWaiting());

            //做完事情了，把自己的状态告知屏障对象，所有人都在屏障这里等着
            cbRef.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

