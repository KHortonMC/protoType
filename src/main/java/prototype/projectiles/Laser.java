package prototype.projectiles;

import prototype.managers.ImageManager;
import prototype.objects.GameObject;

public class Laser extends Projectile {
    public Laser() {
        super();
    }

    public void fire(GameObject owner) {
        super.fire(owner);
        bounding.setWidth(25);
        bounding.setHeight(1000);
        lifespan = 10;
        damage = 20;
        deltaY = 0.1;
        deltaX = 0;
        bounding.translate(0, -bounding.getHeight()/2);
    }
}
