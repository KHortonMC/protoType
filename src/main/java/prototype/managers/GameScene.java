package prototype.managers;

import prototype.objects.AIMech;
import prototype.objects.Background;
import prototype.objects.Barrel;
import prototype.objects.GameObject;
import prototype.support.BoundHelper;
import prototype.support.Rect;
import prototype.weapons.*;

import java.util.ArrayList;
import java.util.Random;

public class GameScene {
    public static void tileBackground(ArrayList<GameObject> objectList) {
        GameManager gm = GameManager.getInstance();

        for (int i = 1; i < 5; i++) {
            Background background = new Background();
            background.loadImage("background"+i);
            background.getBounding().setPosition(500,2000-2000*i);
            objectList.add(background);
        }
    }

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

        AIMech mech = null;

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -100);
        rifle = (Rifle) gm.getResource(Rifle.class);
        mech.pickupWeapon(rifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -800);
        rifle = (Rifle) gm.getResource(Rifle.class);
        mech.pickupWeapon(rifle);
        rifle = (Rifle) gm.getResource(Rifle.class);
        mech.pickupWeapon(rifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -1500);
        DoubleRifle doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -2200);
        MiniGun miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -2900);
        FlameThrower flameThrower = (FlameThrower) gm.getResource(FlameThrower.class);
        mech.pickupWeapon(flameThrower);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -3600);
        RocketLauncher rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -4200);
        miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);
        miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(300, -4600);
        rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);
        doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(100, -5300);
        doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);
        doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);
        rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);
        rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);
        miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);

        mech = (AIMech) gm.getResource(AIMech.class);
        mech.getBounding().setPosition(500, -5300);
        doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);
        doubleRifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        mech.pickupWeapon(doubleRifle);
        rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);
        rocketLauncher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        mech.pickupWeapon(rocketLauncher);
        miniGun = (MiniGun) gm.getResource(MiniGun.class);
        mech.pickupWeapon(miniGun);

        // Clutter
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            double x, y;
            double xspread = 1000;
            double yspread = 8000;
            if (rand.nextBoolean() == true) {
                x = 500 + rand.nextDouble(xspread);
            }
            else {
                x = 100 - rand.nextDouble(xspread);
            }
                y = -rand.nextDouble(yspread);

            GameObject clutter = GameManager.getInstance().getResource(Barrel.class);
            clutter.getBounding().setWidth(50);
            clutter.getBounding().setHeight(50);
            clutter.getBounding().setX(x);
            clutter.getBounding().setY(y);
        }
    }
}
