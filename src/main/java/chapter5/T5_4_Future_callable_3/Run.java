package chapter5.T5_4_Future_callable_3;

import Common.Tools;

import java.util.concurrent.*;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/22 22:02
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserInfo userInfo =new UserInfo();

        MyRunnable myRunnable = new MyRunnable(userInfo);

        ThreadPoolExecutor pool = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        Tools.pBegin();
        Future<UserInfo> future = pool.submit(myRunnable, userInfo);
        userInfo = future.get();
        Tools.pEnd();

        System.out.println(userInfo);

    }
}

class UserInfo{
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserInfo() {
    }

    @Override
    public String toString() {
        return userName+"-"+password;
    }
}

class MyRunnable implements Runnable{

    private UserInfo userInfo;

    MyRunnable(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public void run() {
        userInfo.setPassword("password123");
        userInfo.setUserName("username45");
    }
}

