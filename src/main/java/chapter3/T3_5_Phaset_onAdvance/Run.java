package chapter3.T3_5_Phaset_onAdvance;

import Common.Tools;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 22:27
 * @Version 1.0
 * <p>
 *     OnAdvance()的作用就是通过新的屏障时被调用
 *     return true:破坏屏障功能,就是让后续的屏障功能都失效，相当于没有屏障了
 *     return false: 继续维持屏障的特性
 */
public
class Run {
    public static void main(String[] args) {

        Phaser phaser = new Phaser(2){
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println(" boolean onAdvance(int phase, int registeredParties) 被调用");
                //return true;
                return false;
            }
        };

        MyService myService = new MyService(phaser);
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.setName("A");
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.setName("B");
        myThreadB.start();

    }
}

class MyService {
    private Phaser phaser;

    MyService(Phaser phaser) {
        this.phaser = phaser;
    }

    public void testMethod() {
        try {

            System.out.println("1 begin ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );

            if(Tools.getName().equals("B")){
                Thread.sleep(5000);
            }

            phaser.arriveAndAwaitAdvance();

            System.out.println("1 end ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );


           ///////////////////



            System.out.println("2 begin ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );

            if(Tools.getName().equals("B")){
                Thread.sleep(5000);
            }

            phaser.arriveAndAwaitAdvance();

            System.out.println("2 end ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );


            ////////////////////


            System.out.println("3 begin ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );

            if(Tools.getName().equals("B")){
                Thread.sleep(5000);
            }

            phaser.arriveAndAwaitAdvance();

            System.out.println("4 end ThreadName="
                    + Tools.getName()
                    + Tools.getTime()
            );


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }
}

class MyThreadA extends Thread {
    private MyService myService;

    MyThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

class MyThreadB extends Thread {
    private MyService myService;

    MyThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}