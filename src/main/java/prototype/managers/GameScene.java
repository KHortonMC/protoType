package prototype.managers;

import prototype.objects.AIMech;
import prototype.objects.Barrel;
import prototype.objects.GameObject;
import prototype.support.BoundHelper;
import prototype.support.Rect;
import prototype.weapons.*;

import java.util.Random;

public class GameScene {
    public static void buildScene() {
        GameManager gm = GameManager.getInstance();

        BoundHelper bh = new BoundHelper();
        Rifle rifle = (Rifle) gm.getResource(Rifle.class);
        bh.getBounding().setPosition(300, 200);
        rifle.reposition(bh);
//
//        RocketLauncher launcher = (RocketLauncher) gm.getResource(RocketLauncher.class);
//        bh.getBounding().setPosition(500,200);
//        launcher.reposition(bh);
//
//        MiniGun minigun = (MiniGun) gm.getResource(MiniGun.class);
//        bh.getBounding().setPosition(100, 200);
//        minigun.reposition(bh);
//
//        DoubleRifle doublerifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
//        bh.getBounding().setPosition(700, 200);
//        doublerifle.reposition(bh);
//
//        FlameThrower flameThrower = (FlameThrower) gm.getResource(FlameThrower.class);
//        bh.getBounding().setPosition(900, 200);
//        flameThrower.reposition(bh);
//
//        rifle = (Rifle) gm.getResource(Rifle.class);
//        bh.getBounding().setPosition(300, 00);
//        rifle.reposition(bh);
//
//        launcher = (RocketLauncher) gm.getResource(RocketLauncher.class);
//        bh.getBounding().setPosition(500,00);
//        launcher.reposition(bh);
//
//        minigun = (MiniGun) gm.getResource(MiniGun.class);
//        bh.getBounding().setPosition(100, 00);
//        minigun.reposition(bh);
//
//        doublerifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
//        bh.getBounding().setPosition(700, 00);
//        doublerifle.reposition(bh);
//
//        flameThrower = (FlameThrower) gm.getResource(FlameThrower.class);
//        bh.getBounding().setPosition(900, 00);
//        flameThrower.reposition(bh);

        AIMech mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -50);
        rifle = (Rifle) gm.getResource(Rifle.class);
        mech.pickupWeapon(rifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(-600, 300);
        DoubleRifle drifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(drifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(900, 300);
        MiniGun miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, 900);
        FlameThrower flameThrower = (FlameThrower) gm.getResource(FlameThrower.class);
        mech.pickupWeapon(flameThrower);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -600);
        RocketLauncher rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);

        // Clutter
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            double x, y;
            double spread = 1000;
            if (rand.nextBoolean() == true) {
                x = 500 + rand.nextDouble(spread);
            }
            else {
                x = 100 - rand.nextDouble(spread);
            }
            if (rand.nextBoolean() == true) {
                y = 500 + rand.nextDouble(spread);
            }
            else {
                y = 100 - rand.nextDouble(spread);
            }

            GameObject clutter = GameManager.getInstance().getResource(Barrel.class);
            clutter.getBounding().setWidth(50);
            clutter.getBounding().setHeight(50);
            clutter.getBounding().setX(x);
            clutter.getBounding().setY(y);
        }
    }
}
