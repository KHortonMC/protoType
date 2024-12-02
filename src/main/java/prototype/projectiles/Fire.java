package prototype.projectiles;

import prototype.managers.ImageManager;
import prototype.objects.GameObject;

public class Fire extends Projectile {
    public Fire() {
        super();
        sprite = ImageManager.getImage("fire");
    }

    public void fire(GameObject owner) {
        super.fire(owner);
        bounding.setWidth(30);
        bounding.setHeight(30);
        bounding.setScale(1);
        lifespan = 50 + rand.nextInt(25);
        damage = 1;
        deltaY = -1.5;
        deltaX = 0;
    }

    @Override
    public void update(long now) {
        super.update(now);
        bounding.setScale(bounding.getScale() + 0.01);
    }

    @Override
    protected void handleCollision(GameObject object, double oldX, double oldY) {
        object.takeDamage(this.damage);
    }
}
