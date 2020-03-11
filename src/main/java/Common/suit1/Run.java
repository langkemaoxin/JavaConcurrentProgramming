package Common.suit1;

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
    private Semaphore semaphore=new Semaphore(1);
    public void testMethod(){
        try{
            semaphore.acquire();
            System.out.println("ThreadName="+Thread.currentThread().getName());
            Thread.sleep(1000);

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

