package chapter9.T9_3_Test1;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/4/7 22:08
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.submit(new MyRecursiveAction(1,10));
        Thread.sleep(5000);

    }
}

class MyRecursiveAction extends RecursiveAction{

    private int beginValue;
    private int endValue;

    public MyRecursiveAction(int beginValue, int endValue) {
        super();
        this.beginValue = beginValue;
        this.endValue = endValue;
    }

    @Override
    protected void compute() {
        System.out.println(Thread.currentThread().getName()+"------------");
        if(endValue-beginValue>2){
            int middelNum=(beginValue+endValue)/2;
            System.out.println("leftAction:"+beginValue+" - "+middelNum);
            MyRecursiveAction leftAction = new MyRecursiveAction(beginValue, middelNum);

            System.out.println("rightAction:"+(middelNum+1)+" - "+endValue);
            MyRecursiveAction rightAction = new MyRecursiveAction(middelNum+1, endValue);

            this.invokeAll(leftAction,rightAction);
        }else {
            System.out.println("打印组合为："+beginValue+"-"+endValue);
        }
    }
}
