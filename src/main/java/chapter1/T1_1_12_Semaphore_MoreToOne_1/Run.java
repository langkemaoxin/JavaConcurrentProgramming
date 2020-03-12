package chapter1.T1_1_12_Semaphore_MoreToOne_1;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 8:27
 * @Version 1.0
 *
 * 控制同时进行的并发数
 */
public class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();

        Integer threadCount=5;

        MyThread[] myThreads=new MyThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            myThreads[i]=new MyThread(myService);
        }
        for (int i = 0; i < threadCount; i++) {
            myThreads[i].start();
        }

    }
}

class MyService{
    private Semaphore semaphore=new Semaphore(3);
    public void testMethod(){
        try{
            System.out.println(Thread.currentThread().getName()+"准备");
            System.out.println(Thread.currentThread().getName()+"begin hello "+System.currentTimeMillis());
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"打印"+(i+1));
            }
            System.out.println(Thread.currentThread().getName()+"end hello "+System.currentTimeMillis());
            semaphore.release();

            System.out.println( Thread.currentThread().getName()+"结束");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
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
