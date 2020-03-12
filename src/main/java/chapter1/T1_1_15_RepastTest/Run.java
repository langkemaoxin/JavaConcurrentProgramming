package chapter1.T1_1_15_RepastTest;

import Common.Tools;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 21:48
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MyService myService = new  MyService();

        MyThreadC myThreadC = new MyThreadC(myService);
        myThreadC.start();

        Thread.sleep(2000);

        MyThreadP myThreadP = new MyThreadP(myService);
        myThreadP.start();

//
//        Integer threadCount=5;
//
//        MyThreadP[] myThreadAs=new MyThreadP[threadCount];
//        MyThreadC[] myThreadBs=new MyThreadC[threadCount];
//        for (int i = 0; i < threadCount; i++) {
//            myThreadAs[i]=new MyThreadP(myService);
//            myThreadBs[i]=new MyThreadC(myService);
//        }
//        Thread.sleep(2000);
//
//        for (int i = 0; i < threadCount; i++) {
//            myThreadAs[i].start();
//            myThreadBs[i].start();
//        }
    }
}

class  MyService{
    volatile private Semaphore setSemaphore=new Semaphore(10);
    volatile private Semaphore getSemaphore=new Semaphore(20);

    //想要通知，等待的效果，则必须使用 ReentrantLock锁
    volatile private ReentrantLock lock=new ReentrantLock();
    volatile private Condition setCondition=lock.newCondition();
    volatile private Condition getCondition=lock.newCondition();

    volatile private Object[] producePosition=new Object[4];

    private boolean isEmpty(){
        boolean isEmpty=true;
        for (int i = 0; i < producePosition.length; i++) {
            if(producePosition[i]!=null){
                isEmpty=false;
                break;
            }
        }
        return isEmpty;
    }

    private boolean isFull(){
        boolean isfuLl=true;
        for (int i = 0; i < producePosition.length; i++) {
            if(producePosition[i]==null){
                isfuLl=false;
                break;
            }
        }
        return isfuLl;
    }

    public void set(){
        try{
            //厨师想要做事，则必须先获取一个信号量，这里就控制了厨师的数量
            //setSemaphore.acquire();

            //然后做事情 则是等待通知模型，则使用自旋锁，然后使用对象监视器来对线程之间进行通知，等待

            //获得同步监视器
            lock.lock();
            while (isFull()){
                System.out.println("厨师在等待："+Thread.currentThread().getName());

                //如果当前盘子都是满的，则不需要干活了了，则使用对象监视器，进行等待
                //这里相当于释放了锁
                setCondition.await();
            }


            //一个厨师只生产一份东西出来
            for (int i = 0; i < producePosition.length; i++) {
                if(producePosition[i]==null){
                    producePosition[i]="zz";
                    System.out.println(Thread.currentThread().getName()+"生产了 "+i);
                    break;
                }
            }

            //唤醒服务员工作
            System.out.println("生产者唤醒消费者起来吃饭了");
            getCondition.signalAll();
            lock.unlock();

        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            //setSemaphore.release();
        }
    }

    public void get(){
        try{
            //getSemaphore.acquire();

            lock.lock();

            while (isEmpty()){
                System.out.println("消费者在等待："+Thread.currentThread().getName());
                getCondition.await();
                System.out.println("消费者被唤醒");
            }




            for (int i = 0; i < producePosition.length; i++) {
                if(producePosition[i]!=null){
                    producePosition[i]=null;
                    System.out.println(Thread.currentThread().getName()+"吃了 "+i);
                    break;
                }
            }

            System.out.println("通知厨师继续炒菜");
            setCondition.notifyAll();
            Thread.sleep(2000);
            lock.unlock();

        }catch (InterruptedException ex){
            ex.printStackTrace();
        }finally {
           // getSemaphore.release();
        }
    }
}



class MyThreadP extends Thread{
    private MyService myService;

    MyThreadP(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.set();
    }
}

class MyThreadC extends Thread{
    private MyService myService;

    MyThreadC(MyService myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.get();
    }
}