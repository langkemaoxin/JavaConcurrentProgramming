package chapter1.T1_1_8_Semaphore_TryAcquire_2;

import Common.Tools;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 22:36
 * @Version 1.0
 */
public
class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThreadA myThreadA = new MyThreadA(myService);
        myThreadA.setName("A");
        myThreadA.start();

        MyThreadB myThreadB = new MyThreadB(myService);
        myThreadB.setName("B");
        myThreadB.start();

    }
}

class MyService{
    private Semaphore semaphore=new Semaphore(3);
    public void testMethod(){
        try{

            if(semaphore.tryAcquire(3)) {

                System.out.println("首次进入 ThreadName=" + Thread.currentThread().getName());
                Tools.longTask();
                semaphore.release(3);
                
            }else{
                System.out.println("进不去 ThreadName=" + Thread.currentThread().getName());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
    }
}

class MyThreadA extends Thread{
    private MyService myService;

    MyThreadA(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}

class MyThreadB extends Thread{
    private MyService myService;

    MyThreadB(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.testMethod();
    }
}
