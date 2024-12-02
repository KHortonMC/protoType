package prototype.weapons;

import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.objects.GameObject;
import prototype.projectiles.Missile;
import prototype.projectiles.Projectile;
import prototype.projectiles.Rocket;
import prototype.support.Bound;
import prototype.support.Cooldown;
import prototype.support.Rect;

import java.util.ArrayList;

public class RocketLauncher extends Launcher {
    public RocketLauncher() {
        super();
        fireCooldown = new Cooldown(10,0);
        reloadCooldown = new Cooldown(1,150);
        ammo = maxAmmo = 5;
        this.maxScatter = 40;
        this.iconName = "rocketlauncher";
        sprite = ImageManager.getImage(this.iconName);
        this.bounding.setWidth(80);
        this.bounding.setHeight(80);
    }

    @Override
    protected Projectile getProjectile() {
        return (Rocket) GameManager.getInstance().getResource(Rocket.class);
    }
}


