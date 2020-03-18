package chapter3.T3_8_Phaser_test4;

import Common.Tools;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 23:09
 * @Version 1.0
 *
 * getArrivedParties 已经被使用的parties个数
 * getUnarrivedParties 未被使用的parties个数
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();

        Integer threadCount=5;

        MyThread[] myThreads=new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            myThreads[i]=new MyThread(phaser);
        }
        for (int i = 0; i < threadCount; i++) {
            myThreads[i].start();
        }

        Tools.sleep();

        System.out.println("已到达："+phaser.getArrivedParties());

        System.out.println("wei到达："+phaser.getUnarrivedParties());
    }
}

class MyThread extends Thread{
    private Phaser phaser;

    MyThread(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println(Tools.getName()+"Begin");

        phaser.arriveAndAwaitAdvance();

        System.out.println(Tools.getName()+"End");
    }
}
