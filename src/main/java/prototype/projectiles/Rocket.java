package prototype.projectiles;

import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.objects.GameObject;

public class Rocket extends Projectile {
    public Rocket() {
        super();
        this.isCollidable = false;
        sprite = ImageManager.getImage("rocket");
    }

    public void fire(GameObject owner) {
        super.fire(owner);
        bounding.setWidth(12);
        bounding.setHeight(35);
        lifespan = 30;
        damage = 10;
        deltaY = -7;
        deltaX = 0;
    }

    @Override
    protected void handleDestruction() {
        super.handleDestruction();
        Explosion explosion = (Explosion) GameManager.getInstance().getResource(Explosion.class);
        explosion.setTeam(this.team);
        explosion.setRect(this.bounding);
        explosion.setSize(100);
    }
}
