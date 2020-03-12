package chapter1.T1_2_1_Exchange_3.T1_2_1_Exchange_2;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 22:53
 * @Version 1.0
 *
 *  5秒钟过后，如果还是不能交换的话，则报异常超时
 *  exchanger.exchange("中国人A",5, TimeUnit.SECONDS));
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
            System.out.println("A线程："+exchanger.exchange("中国人A",5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

