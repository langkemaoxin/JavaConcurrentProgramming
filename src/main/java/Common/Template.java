package Common;

import java.util.concurrent.Semaphore;

/**
 * @ClassName Template
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 8:10
 * @Version 1.0
 */
public class Template {
}

class MyService{
    private Semaphore semaphore=new Semaphore(1);
    public void testMethod(){
        try{

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


/**
 * 运行方式一：
 */
class Run{
    public static void main(String[] args) {
        MyService myService = new MyService();
        MyThread myThread = new MyThread(myService);
        myThread.start();

    }
}


/**
 * 运行方式二：
 */
class Run2{
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