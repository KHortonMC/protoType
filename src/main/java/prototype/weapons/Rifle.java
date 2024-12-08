package prototype.weapons;

import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.projectiles.Bullet;
import prototype.projectiles.Projectile;
import prototype.support.Cooldown;

public class Rifle extends Launcher {
    public Rifle() {
        super();
        fireCooldown = new Cooldown(15,0);
        reloadCooldown = new Cooldown(30,30);
        ammo = maxAmmo = 4;
        maxScatter = 4;
        this.iconName = "rifle";
        sprite = ImageManager.getImage(this.iconName);
        this.bounding.setWidth(80);
        this.bounding.setHeight(80);
    }

    @Override
    protected Projectile getProjectile() {
        return (Bullet) GameManager.getInstance().getResource(Bullet.class);
    }
}
