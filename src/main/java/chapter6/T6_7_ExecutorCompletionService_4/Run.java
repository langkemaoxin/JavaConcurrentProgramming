package chapter6.T6_7_ExecutorCompletionService_4;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/23 22:27
 * @Version 1.0
 *
 * 接口CompletionService完全可以避免FutureTask类阻塞的缺点，
 * 可更加有效地处理Future的返回值，也就是哪个任务先执行完，CompletionService就先取得这个任务的返回值
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserInfo userInfo = new UserInfo();
        MyRunnable myRunnable = new MyRunnable(userInfo);

        ExecutorService executorService = Executors.newCachedThreadPool();

        //此方法是为了解决 FutureTask 阻塞获取值，并且
        CompletionService csRef = new ExecutorCompletionService(executorService);
        Future<UserInfo> future = csRef.submit(myRunnable, userInfo);
        System.out.println(future.get().getPassword()+" _ "+future.get().getUsername());

        //此方法是阻塞型的，并且提交的方法是按照顺序来的
        //Future<UserInfo> submit = executorService.submit(myRunnable, userInfo);

    }
}

class UserInfo {
    private String username;
    private String password;

    public UserInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserInfo() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class MyRunnable implements Runnable {

    private UserInfo userInfo;

    MyRunnable(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        userInfo.setUsername("usernameValue");
        userInfo.setPassword("userpasswordValue");
        System.out.println("运行了");
    }
}