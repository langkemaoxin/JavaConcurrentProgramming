package chapter2.T2_2_2_CyclicBarrier_awaitAfterReset_tranning;

import Common.Tools;

import java.util.Arrays;
import java.util.List;
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
 * 这里只能用来控制线程统一出发时间，而不能控制每个个线程的最终完成时间
 */
public class Run {
    public static void main(String[] args) {
        CyclicBarrier cbRef=new CyclicBarrier(5, new Runnable() {
            public void run() {
                System.out.println("出发");
            }
        });

        List<String>  userIds1= Arrays.asList("1", "2", "3");

        for (int i = 0; i < 9; i++) {
            MyThread myThread = new MyThread(cbRef, Arrays.asList(i + ""));

            myThread.start();
            Tools.randomThread();

        }
    }
}

class MyThread extends Thread{
    private CyclicBarrier cbRef;
    private List<String> userIds;

    MyThread(CyclicBarrier cbRef, List<String> userIds) {
        this.cbRef = cbRef;
        this.userIds = userIds;
    }

    @Override
    public void run() {
        try {
            System.out.println(Tools.getName()+"到达屏障点");
            cbRef.await();
            System.out.println(Tools.getName()+"开始执行");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

