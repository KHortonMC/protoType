package prototype.projectiles;

import prototype.objects.GameObject;
import prototype.managers.ImageManager;
import prototype.support.Rect;

public class Missile extends Projectile {
    public Missile() {
        super();
        sprite = ImageManager.getImage("missile");
    }

    public void fire(GameObject owner) {
        super.fire(owner);
        bounding.setWidth(25);
        bounding.setHeight(25);
        lifespan = 100;
        damage = 100;
        deltaY = -3;
        deltaX = 0;
    }

    @Override
    protected void handleCollision(GameObject object, double oldX, double oldY) {
        super.handleCollision(object, oldX, oldY);
        // todo: make an explosion!
    }
}
