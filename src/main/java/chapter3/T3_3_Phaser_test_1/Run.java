package chapter3.T3_3_Phaser_test_1;

import Common.Tools;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/17 07:55
 * @Version 1.0
 *
 * ABC 三人同时跨过了第一个屏障
 * AB继续在第二个屏障前等待，但是C已经没有屏障了
 *
 * 这样就导致了AB在第二个屏障前无限等待
 *
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        PrintTools.phaser = phaser;

        MyThreadA myThreadA = new MyThreadA(phaser);
        myThreadA.setName("A");
        myThreadA.start();


        MyThreadB myThreadB = new MyThreadB(phaser);
        myThreadB.setName("B");
        myThreadB.start();

        MyThreadC myThreadC = new MyThreadC(phaser);
        myThreadC.setName("C");
        myThreadC.start();
    }
}


class PrintTools {
    public static Phaser phaser;

    public static void methodA() {
        System.out.println(Tools.getName() + " A1 begin=" + Tools.getTime());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Tools.getName() + " A1 end=" + Tools.getTime());


        System.out.println(Tools.getName() + " A2 begin=" + Tools.getTime());
        phaser.arriveAndAwaitAdvance();
        System.out.println(Tools.getName() + " A2 end=" + Tools.getTime());
    }


    public static void methodB() {
        System.out.println(Tools.getName() + " A1 begin=" + Tools.getTime());
        Tools.sleep();
        phaser.arriveAndAwaitAdvance();
        System.out.println(Tools.getName() + " A1 end=" + Tools.getTime());



    }
}


class MyThreadA extends Thread {
    private Phaser phaser;

    MyThreadA(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        PrintTools.methodA();
    }
}


class MyThreadB extends Thread {
    private Phaser phaser;

    MyThreadB(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        PrintTools.methodA();
    }
}

class MyThreadC extends Thread {
    private Phaser phaser;

    MyThreadC(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        PrintTools.methodB();
    }
}