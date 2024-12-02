package prototype.managers;

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

        RocketLauncher launcher = (RocketLauncher) gm.getResource(RocketLauncher.class);
        bh.getBounding().setPosition(500,200);
        launcher.reposition(bh);

        MiniGun minigun = (MiniGun) gm.getResource(MiniGun.class);
        bh.getBounding().setPosition(100, 200);
        minigun.reposition(bh);

        DoubleRifle doublerifle = (DoubleRifle) gm.getResource(DoubleRifle.class);
        bh.getBounding().setPosition(700, 200);
        doublerifle.reposition(bh);

        FlameThrower flameThrower = (FlameThrower) gm.getResource(FlameThrower.class);
        bh.getBounding().setPosition(900, 200);
        flameThrower.reposition(bh);

        // Clutter
        Random rand = new Random();

        for (int i = 0; i < 50; i++) {
            double x, y;
            if (rand.nextBoolean() == true) {
                x = 500 + rand.nextDouble(600);
            }
            else {
                x = 100 - rand.nextDouble(600);
            }
            if (rand.nextBoolean() == true) {
                y = 500 + rand.nextDouble(600);
            }
            else {
                y = 100 - rand.nextDouble(600);
            }

            GameObject clutter = GameManager.getInstance().getResource(Barrel.class);
            clutter.getBounding().setWidth(50);
            clutter.getBounding().setHeight(50);
            clutter.getBounding().setX(x);
            clutter.getBounding().setY(y);
        }
    }
}
