package prototype.weapons;

import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.projectiles.Fire;
import prototype.projectiles.Projectile;
import prototype.support.Cooldown;

public class FlameThrower extends Launcher {
    public FlameThrower() {
        super();
        fireCooldown = new Cooldown(5,0);
        reloadCooldown = new Cooldown(5,90);
        ammo = maxAmmo = 30;
        maxScatter = 10;
        this.iconName = "flamethrower";
        sprite = ImageManager.getImage(this.iconName);
        this.bounding.setWidth(80);
        this.bounding.setHeight(80);
    }

    @Override
    protected Projectile getProjectile() {
        return (Fire) GameManager.getInstance().getResource(Fire.class);
    }
}
