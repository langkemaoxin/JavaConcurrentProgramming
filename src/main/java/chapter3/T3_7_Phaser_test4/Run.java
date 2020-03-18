package chapter3.T3_7_Phaser_test4;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 23:09
 * @Version 1.0
 *
 * bulkRegister 批量添加 parties值
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(5);
        System.out.println(phaser.getRegisteredParties());

        phaser.bulkRegister(10);

        System.out.println(phaser.getRegisteredParties());
    }
}
