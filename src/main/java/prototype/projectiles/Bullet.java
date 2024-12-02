package prototype.projectiles;

import prototype.managers.ImageManager;
import prototype.objects.GameObject;
import prototype.support.Rect;

public class Bullet extends Projectile {
    public Bullet() {
        super();
        sprite = ImageManager.getImage("bullet");
    }

    public void fire(GameObject owner) {
        super.fire(owner);
        bounding.setWidth(5);
        bounding.setHeight(30);
        lifespan = 80;
        damage = 5;
        deltaY = -7;
        deltaX = 0;
    }
}
