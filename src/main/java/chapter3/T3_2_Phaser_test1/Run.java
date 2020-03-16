package chapter3.T3_2_Phaser_test1;

import Common.Tools;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/16 22:53
 * @Version 1.0
 *
 * 在多个线程同时跑的时候，使用Phaser维护他们的阶段状态
 * 可以实现一段一段的
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


        System.out.println(Tools.getName() + " A2 begin=" + Tools.getTime());
        Tools.sleep();
        phaser.arriveAndAwaitAdvance();
        System.out.println(Tools.getName() + " A2 end=" + Tools.getTime());
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