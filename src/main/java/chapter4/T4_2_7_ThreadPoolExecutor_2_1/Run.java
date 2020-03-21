package chapter4.T4_2_7_ThreadPoolExecutor_2_1;

import Common.Tools;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/21 17:12
 * @Version 1.0
 *
 * 案例输出结果：
 * D:\develop\jdk1.8\bin\java.exe "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2019.1.3\lib\idea_rt.jar=62088:C:\Program Files\JetBrains\IntelliJ IDEA 2019.1.3\bin" -Dfile.encoding=UTF-8 -classpath D:\develop\jdk1.8\jre\lib\charsets.jar;D:\develop\jdk1.8\jre\lib\deploy.jar;D:\develop\jdk1.8\jre\lib\ext\access-bridge-64.jar;D:\develop\jdk1.8\jre\lib\ext\cldrdata.jar;D:\develop\jdk1.8\jre\lib\ext\dnsns.jar;D:\develop\jdk1.8\jre\lib\ext\jaccess.jar;D:\develop\jdk1.8\jre\lib\ext\jfxrt.jar;D:\develop\jdk1.8\jre\lib\ext\localedata.jar;D:\develop\jdk1.8\jre\lib\ext\nashorn.jar;D:\develop\jdk1.8\jre\lib\ext\sunec.jar;D:\develop\jdk1.8\jre\lib\ext\sunjce_provider.jar;D:\develop\jdk1.8\jre\lib\ext\sunmscapi.jar;D:\develop\jdk1.8\jre\lib\ext\sunpkcs11.jar;D:\develop\jdk1.8\jre\lib\ext\zipfs.jar;D:\develop\jdk1.8\jre\lib\javaws.jar;D:\develop\jdk1.8\jre\lib\jce.jar;D:\develop\jdk1.8\jre\lib\jfr.jar;D:\develop\jdk1.8\jre\lib\jfxswt.jar;D:\develop\jdk1.8\jre\lib\jsse.jar;D:\develop\jdk1.8\jre\lib\management-agent.jar;D:\develop\jdk1.8\jre\lib\plugin.jar;D:\develop\jdk1.8\jre\lib\resources.jar;D:\develop\jdk1.8\jre\lib\rt.jar;D:\学习区\Java\JavaConcurrentProgramming\target\classes chapter4.T4_2_7_ThreadPoolExecutor_3.Run
 * pool-1-thread-1 Run 1584782805343
 * pool-1-thread-3 Run 1584782805343
 * pool-1-thread-4 Run 1584782805343
 * pool-1-thread-5 Run 1584782805343
 * pool-1-thread-2 Run 1584782805343
 * pool-1-thread-6 Run 1584782805343
 * pool-1-thread-7 Run 1584782805343
 * A:7
 * A:7
 * A:0
 * B:7
 * B:7
 * B:0
 * 车中可载人的标准人数:7
 * 车中可载人的最大人数:8
 * 车中正在载的人数:7
 * 扩展车中正在载的人数:0
 *
 * 案例说明：
 * 执行的数量小于核心数量，其他参数忽略
 *
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " Run " + Tools.getTime());
                Tools.sleep();
            }
        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(7, 8, 5, TimeUnit.SECONDS, new SynchronousQueue<>());

        executor.execute(runnable);//1
        executor.execute(runnable);//2
        executor.execute(runnable);//3
        executor.execute(runnable);//4
        executor.execute(runnable);//5
        executor.execute(runnable);//6
        executor.execute(runnable);//7

        Thread.sleep(300);

        System.out.println("A:" + executor.getCorePoolSize());
        System.out.println("A:" + executor.getPoolSize());
        System.out.println("A:" + executor.getQueue().size());

        Thread.sleep(10000);

        System.out.println("B:" + executor.getCorePoolSize());
        System.out.println("B:" + executor.getPoolSize());
        System.out.println("B:" + executor.getQueue().size());


        System.out.println("车中可载人的标准人数:" + executor.getCorePoolSize());
        System.out.println("车中可载人的最大人数:" + executor.getMaximumPoolSize());
        System.out.println("车中正在载的人数:" + executor.getPoolSize());
        System.out.println("扩展车中正在载的人数:" + executor.getQueue().size());

    }
}
