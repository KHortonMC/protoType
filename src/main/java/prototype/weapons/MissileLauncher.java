package prototype.weapons;

import prototype.managers.GameManager;
import prototype.projectiles.Bullet;
import prototype.projectiles.Missile;
import prototype.projectiles.Projectile;
import prototype.support.Cooldown;

public class MissileLauncher extends Launcher {
    public MissileLauncher() {
        super();
        fireCooldown = new Cooldown(40,0);
        reloadCooldown = new Cooldown(40,90);
        this.ammo = 10;
    }

    @Override
    protected Projectile getProjectile() {
        return (Missile) GameManager.getInstance().getResource(Missile.class);
    }
}