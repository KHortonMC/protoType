package prototype.objects;

import prototype.support.Cooldown;
import prototype.support.Rect;

public class Player extends Mecha {
    public Player() {
        super();
    }

    public Player(Rect bounding) {
        super(bounding);
    }

    @Override
    protected void initialize() {
        this.team = Team.PLAYER;
        super.initialize();
        currentHealth = maxHealth = 600;

        animCooldown = new Cooldown(15,0);
        healCooldown = new Cooldown(1, 60);
    }
}
