package chapter2.T2_1_4_CountDownLatch_Test2_3_ext;

import Common.Tools;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/15 9:26
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch comingTag = new CountDownLatch(10);
        CountDownLatch waitTag = new CountDownLatch(1);
        CountDownLatch waitRunTag = new CountDownLatch(10);
        CountDownLatch beginTag = new CountDownLatch(1);
        CountDownLatch endTag = new CountDownLatch(10);
        MyThread[] theadArray=new MyThread[10];
        for (int i = 0; i < theadArray.length; i++) {
            theadArray[i]=new MyThread(comingTag,waitTag,waitRunTag,beginTag,endTag);
            theadArray[i].start();
        }

        System.out.println("裁判员在等待选手的到来");

        //这里必须等待所有人都就位才行，也就是每个线程必须对其进行减一
        comingTag.await();

        System.out.println("所有的运动员都到齐了，可以准备开始了");
        Thread.sleep(5000);

        //通知所有人进行下一个阶段，否则他们现在都卡在这里了
        waitTag.countDown();
        System.out.println("各就各位");

        //这里必须等待所有人都准备好
        waitRunTag.await();
        Thread.sleep(2000);

        System.out.println("发令枪响起");
        beginTag.countDown();

        endTag.await();

        System.out.println("所有运动员都到通过终点了");

    }
}

class MyThread extends Thread {
    private CountDownLatch comingTag; //等待所有人就位
    private CountDownLatch waitTag; //准备开始
    private CountDownLatch waitRunTag;//等待起跑
    private CountDownLatch beginTag; //开始跑
    private CountDownLatch endTag; //结束跑


    public MyThread(CountDownLatch comingTag, CountDownLatch waitTag, CountDownLatch waitRunTag, CountDownLatch beginTag, CountDownLatch endTag) {
        this.comingTag = comingTag;
        this.waitTag = waitTag;
        this.waitRunTag = waitRunTag;
        this.beginTag = beginTag;
        this.endTag = endTag;
    }

    @Override
    public void run() {
        try {
            System.out.println("运动员使用不同交通工具不同速度到达起跑点，正向这边走！");
            Thread.sleep((int) (Math.random() * 10000));

            System.out.println(Tools.getName() + "到达起跑点了");
            comingTag.countDown();
            //至此，运动员到了




            System.out.println(Tools.getName() + "等待裁判说准备！");
            waitTag.await(); //计数减一，必须等待所有人都到齐了，才开始

            System.out.println(Tools.getName() +"各就各位，准备起跑姿势！");
            Thread.sleep((int) (Math.random() * 10000));
            waitRunTag.countDown();

            beginTag.await();
            System.out.println(Tools.getName() + "出发了");
            Thread.sleep((int) (Math.random() * 10000));
            endTag.countDown();

            System.out.println(Tools.getName() + "到达终点,我是第"+ (10-endTag.getCount())+"名");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
