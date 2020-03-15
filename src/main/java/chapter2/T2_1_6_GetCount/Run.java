package chapter2.T2_1_6_GetCount;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/15 11:20
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        CountDownLatch downLatch=new CountDownLatch(3);
        System.out.println(downLatch.getCount());
        downLatch.countDown();

        System.out.println(downLatch.getCount());
        downLatch.countDown();

        System.out.println(downLatch.getCount());
        downLatch.countDown();

        System.out.println(downLatch.getCount());
        downLatch.countDown();

        System.out.println(downLatch.getCount());
        downLatch.countDown();
    }
}
