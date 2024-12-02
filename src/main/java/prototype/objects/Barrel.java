package prototype.objects;

import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.projectiles.Explosion;

public class Barrel extends GameObject {
    public Barrel() {
        sprite = ImageManager.getImage("barrel");
        maxHealth = currentHealth = 10;
        team = Team.WORLD;
    }

    @Override
    public void takeCollision(GameObject instigator) {
        if (instigator.maxHealth > 0) {
            handleDestruction();
        }
    }

    @Override
    protected void handleDestruction() {
        super.handleDestruction();
        Explosion explosion = (Explosion) GameManager.getInstance().getResource(Explosion.class);
        explosion.setTeam(this.team);
        explosion.setRect(this.bounding);
        explosion.setSize(200);
    }
}
