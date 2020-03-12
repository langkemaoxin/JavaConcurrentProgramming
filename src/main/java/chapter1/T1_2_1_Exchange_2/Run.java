package chapter1.T1_2_1_Exchange_2;

import java.util.concurrent.Exchanger;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 22:53
 * @Version 1.0
 *
 * 最简单的用法
 * 1. 新建一个 Exchanger对象
 * 2. 新建两个线程，传入这个 Exchanger对象
 * 3. 两个线程就会互相交换数据
 */
public class Run {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ThreadA threadA = new ThreadA(exchanger);
        threadA.start();

        ThreadB threadb = new ThreadB(exchanger);
        threadb.start();

        System.out.println("main end");
    }
}

class ThreadA extends Thread{
    private Exchanger<String> exchanger;

    public ThreadA(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("A线程："+exchanger.exchange("中国人A"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ThreadB extends Thread{
    private Exchanger<String> exchanger;

    public ThreadB(Exchanger<String> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            System.out.println("B线程："+exchanger.exchange("中国人B"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}