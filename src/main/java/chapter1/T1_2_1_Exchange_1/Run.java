package chapter1.T1_2_1_Exchange_1;

import java.util.concurrent.Exchanger;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 22:53
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();
        ThreadA threadA = new ThreadA(exchanger);
        threadA.start();
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

            //这里会一直阻塞线程
            System.out.println("在线程A得到线程B的值"+exchanger.exchange("中国人A"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


