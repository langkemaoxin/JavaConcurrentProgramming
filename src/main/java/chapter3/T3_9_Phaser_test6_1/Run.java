package chapter3.T3_9_Phaser_test6_1;

import java.util.concurrent.Phaser;

/**
 * @ClassName Run
 * @Description TODO
 * @Author GY.C
 * @Date 2020/3/18 23:20
 * @Version 1.0
 */
public class Run {
    public static void main(String[] args) {
        Phaser phaser = new Phaser(1) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                System.out.println("到达了未通过 phase=" + phase
                        + " registerParties=" + registeredParties
                );
                return super.onAdvance(phase, registeredParties);
            }
        };

        System.out.println("A1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("A1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        System.out.println("A2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("A2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );

        ////////////////////

        System.out.println("B1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("B1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        System.out.println("B2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("B2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );


////////////////////

        System.out.println("C1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("C1 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + " getArrivedPaties=" + phaser.getArrivedParties()
        );

        System.out.println("C2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );

        phaser.arrive();

        System.out.println("C2 getPhase=" + phaser.getPhase()
                + " getRegisteredParties=" + phaser.getRegisteredParties()
                + "getArrivedPaties=" + phaser.getArrivedParties()
        );

    }
}
