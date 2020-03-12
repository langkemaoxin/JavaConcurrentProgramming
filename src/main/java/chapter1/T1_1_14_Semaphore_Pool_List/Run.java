package chapter1.T1_1_14_Semaphore_Pool_List;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/12 20:55
 * @Version 1.0
 *
 *
 *
 */
class Run{
    public static void main(String[] args) {
        ListPool myService = new ListPool();

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

class ListPool {
    private int poolMaxSize=3;
    private int semaphorePermits=5;
    private List<String> list=new ArrayList<String>();

    //控制同一时间的并发数量
    private Semaphore consurrencySemaphore =new Semaphore(semaphorePermits);
    private ReentrantLock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();

    public ListPool() {
        super();
        for (int i = 0; i < poolMaxSize; i++) {
            list.add("pool_"+i);
        }
    }

    public String get(){
        String getString=null;
        try{
            consurrencySemaphore.acquire();
            lock.lock();

            while (list.size()==0){
                condition.await();
            }
            getString = list.remove(0);
            lock.unlock();

        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return getString;
    }

    public void put(String stringValue){
        lock.lock();
        list.add(stringValue);
        condition.signalAll();
        lock.unlock();
        consurrencySemaphore.release();
    }



}

class MyThread extends Thread{
    private ListPool listPool;

    MyThread(ListPool listPool) {
        super();
        this.listPool = listPool;
    }

    @Override
    public void run() {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String getString = listPool.get();
            System.out.println(Thread.currentThread().getName()+" 取得值"+getString);
            listPool.put(getString);
        }

    }
}

