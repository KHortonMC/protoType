package prototype.weapons;

import javafx.scene.canvas.GraphicsContext;
import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.projectiles.Bullet;
import prototype.projectiles.Projectile;
import prototype.support.Bound;
import prototype.support.Cooldown;
import prototype.support.Rect;

public class MiniGun extends Launcher {

    public MiniGun() {
        super();
        fireCooldown = new Cooldown(5,0);
        reloadCooldown = new Cooldown(3,160);
        ammo = maxAmmo = 40;
        maxScatter = 10;
        iconName = "minigun";
        sprite = ImageManager.getImage(iconName);
        this.bounding.setWidth(80);
        this.bounding.setHeight(80);
    }

    @Override
    protected Projectile getProjectile() {
        return (Bullet) GameManager.getInstance().getResource(Bullet.class);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
    }
}
