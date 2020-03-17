package chapter3.T3_4_Phaser_test_2;

import Common.Tools;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/17 07:55
 * @Version 1.0
 *
 * public int arriveAndDeregister() {
 *         return doArrive(ONE_DEREGISTER);
 * }
 *
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

        System.out.println("A:"+phaser.getRegisteredParties());
        phaser.arriveAndDeregister();

        System.out.println("B:"+phaser.getRegisteredParties());


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