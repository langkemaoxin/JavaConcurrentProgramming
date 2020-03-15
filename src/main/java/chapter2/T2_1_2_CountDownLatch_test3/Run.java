package chapter2.T2_1_2_CountDownLatch_test3;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/14 18:29
 * @Version 1.0
 *
 * 裁判员要等待所有运动员全部冲过终点的效果
 */
public
class Run{
    public static void main(String[] args) throws InterruptedException {

        //定义了10个赛跑者
        CountDownLatch maxRunner = new CountDownLatch(10);

        //让10个赛跑者同时出发
        MyThread[] myThreads = new MyThread[10];
        for (int i = 0; i < 10; i++) {
            myThreads[i] = new MyThread(maxRunner);
            myThreads[i].setName("线程 "+i);
            myThreads[i].start();
        }

        //等待count的数字降至0才结束
        maxRunner.await();
        System.out.println("都回来啦");

    }
}

class MyThread extends Thread{
    private CountDownLatch maxRunner;

    MyThread(CountDownLatch maxRunner) {
        this.maxRunner = maxRunner;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            maxRunner.countDown();

            System.out.println("选手"+Thread.currentThread().getName()+"冲过了终点,获得名次为："+maxRunner.getCount());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

