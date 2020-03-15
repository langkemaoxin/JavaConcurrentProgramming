package chapter2.T2_1_3_CountDownLatch_test2;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/14 18:40
 * @Version 1.0
 *
 * 这个例子有点意思：
 * 必须等待10个线程，全部准备好后，才能正式开始！
 *
 * 只需要一把信号枪就完事了，所有运动员都在等同一个信号
 */
public
class Run{
    public static void main(String[] args) throws InterruptedException {
        //定义了10个赛跑者
        MyService myService = new MyService();

        //让10个赛跑者同时出发+++
        MyThread[] myThreads = new MyThread[5];
        for (int i = 0; i < 5; i++) {
            myThreads[i] = new MyThread(myService);
            myThreads[i].setName("线程 "+i);
            myThreads[i].start();
        }

        Thread.sleep(2000);

        myService.begin();

    }
}

class MyService{
    private CountDownLatch down=new CountDownLatch(1);

    public void testMethod(){
        try{

            System.out.println("ThreadName="+Thread.currentThread().getName()+"准备");
            down.await();
            System.out.println("ThreadName="+Thread.currentThread().getName()+"结束");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
        }
    }

    public void begin(){
        down.countDown();
    }

}

class MyThread extends Thread{
    private MyService myService;

    MyThread(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
