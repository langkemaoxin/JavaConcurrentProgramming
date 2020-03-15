package Common.suit2;

import java.util.concurrent.Semaphore;

/**
 *
 * 套餐一：
 *
 */

class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();

    }
}

class MyService{
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

