package chapter2.T2_2_4_CyclicBarrier_run2;

import Common.Tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/16 8:30
 * @Version 1.0
 * <p>
 * 发生异常后，不影响其他线程
 *
 * 如果有一个线程由于中断或者超时提前离开了屏障点，其他所有在屏障点等待的线程会抛出BrokenBarrierExcepion
 * 或者 InterruptedExcepion异常，并且离开屏障点
 */
public class Run {
    public static void main(String[] args) {
        int parties = 4;
        CyclicBarrier cbRef = new CyclicBarrier(parties, new Runnable() {
            public void run() {
                System.out.println("都到了");
            }
        });

        MyService myService = new MyService(cbRef);

        MyThread[] threadArray = new MyThread[4];

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new MyThread(myService);
            threadArray[i].start();
        }


    }
}

class MyService {
    private CyclicBarrier cbRef;

    MyService(CyclicBarrier cbRef) {
        this.cbRef = cbRef;
    }

    private void beginRun(int count) {
        try {
            System.out.println(Tools.getName() + "到了， 在等待其他人都开始了再跑");
            if (Tools.getName().equals("Thread-2")) {
                System.out.println("thread-2 进来了");
                Tools.sleep();
                //Integer.parseInt("a");
                Thread.currentThread().interrupt();
            }

            //等待每个线程，都来这里的屏障前进行等待
            cbRef.await();

            System.out.println("都到了，开始跑");
            System.out.println(Tools.getName() + " 达到终点，并结束第" + count + " 赛段");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void testA() {
        for (int i = 0; i < 1; i++) {
            beginRun(i);
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
        myService.testA();
    }
}