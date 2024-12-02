package prototype.weapons;

import prototype.managers.GameManager;
import prototype.projectiles.Laser;
import prototype.projectiles.Missile;
import prototype.projectiles.Projectile;
import prototype.support.Cooldown;

public class LaserCannon extends Launcher {
    public LaserCannon() {
        super();
        fireCooldown = new Cooldown(15, 0);
        reloadCooldown = new Cooldown(20, 30);
        this.ammo = 10;
    }

    @Override
    protected Projectile getProjectile() {
        return (Laser) GameManager.getInstance().getResource(Laser.class);
    }
}
