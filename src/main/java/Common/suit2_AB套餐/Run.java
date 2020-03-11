package Common.suit2_AB套餐;

import java.util.concurrent.Semaphore;

/**
 *
 * 套餐一：
 *
 */

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
    private Semaphore semaphore=new Semaphore(1);
    public void testMethod(){
        try{

            System.out.println("ThreadName="+Thread.currentThread().getName());
            Thread.sleep(1000);

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

