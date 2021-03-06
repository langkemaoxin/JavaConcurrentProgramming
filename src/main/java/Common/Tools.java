package Common;

/**
 * @ClassName Tools
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/11 7:43
 * @Version 1.0
 */
public class Tools {
    /**
     * 执行耗时任务
     */
    public static void longTask(int count){

        for (int j = 0; j < count; j++) {
            for (int i = 0; i < Integer.MAX_VALUE/999; i++) {
                String s = new String();
                Math.random();
            }
        }
    }

    public static void longTask(){
        longTask(1);
    }

    public static String getName(){
        return Thread.currentThread().getName();
    }

    public static long getTime(){
        return System.currentTimeMillis();
    }

    public static void randomThread(){
        try {
            Thread.sleep((int)(Math.random())*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void pBegin(){
        System.out.println("begin "+ Tools.getName()+Tools.getTime());
    }

    public static void pEnd(){
        System.out.println("end "+ Tools.getName()+Tools.getTime());
    }
}
