package prototype.projectiles;

import prototype.objects.GameObject;
import prototype.objects.LocomotionObject;
import prototype.support.Rect;

public class Projectile extends LocomotionObject {
    public Projectile() {
        super();
    }

    GameObject owner = null;
    long lifespan = 0;
    long damage = 0;

    public void fire(GameObject owner) {
        this.owner = owner;
        this.team = owner.getTeam();
        bounding.setWidth(0);
        bounding.setHeight(0);
        lifespan = 0;
        damage = 0;
        deltaY = 0;
        deltaX = 0;
    }

    @Override
    public boolean collidesWith(GameObject other) {
        if (other == owner) return false;
        return super.collidesWith(other);
    }

    @Override
    public void update(long now) {
        super.update(now);

        lifespan--;
        if (lifespan == 0) {
            handleDestruction();
        }
    }

    @Override
    protected void handleCollision(GameObject object, double oldX, double oldY) {
        object.takeDamage(this.damage);
        this.handleDestruction();
    }
}
