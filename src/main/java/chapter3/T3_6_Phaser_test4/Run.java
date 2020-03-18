package chapter3.T3_6_Phaser_test4;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 23:09
 * @Version 1.0
 *
 * getRegisteredParties 获取注册的parties数量
 * register 动态添加一个 parties值
 *
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        System.out.println(phaser.getRegisteredParties());

        phaser.register();

        System.out.println(phaser.getRegisteredParties());
    }
}
