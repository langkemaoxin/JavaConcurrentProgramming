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

        for (int i = 0; i < Integer.MAX_VALUE/50; i++) {
            String s = new String();
            Math.random();
        }
    }

    public static void longTask(){
        longTask(1);
    }
}
