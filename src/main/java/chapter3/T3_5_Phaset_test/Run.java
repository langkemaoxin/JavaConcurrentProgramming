package chapter3.T3_5_Phaset_test;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 22:27
 * @Version 1.0
 *
 * getParse()获取得是已经到达了第几个屏障
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(1);
        ThreadA threadA = new ThreadA(phaser);
        threadA.start();
    }
}

class ThreadA extends Thread{
    private Phaser phaser;

    ThreadA(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.println("A begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+phaser.getPhase());


        System.out.println("A begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+phaser.getPhase());


        System.out.println("A begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+phaser.getPhase());


        System.out.println("A begin");
        phaser.arriveAndAwaitAdvance();
        System.out.println("A end phase value="+phaser.getPhase());


    }
}
