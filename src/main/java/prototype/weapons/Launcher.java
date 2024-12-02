package prototype.weapons;

import prototype.managers.GameManager;
import prototype.objects.GameObject;
import prototype.projectiles.Bullet;
import prototype.projectiles.Projectile;
import prototype.support.Cooldown;
import prototype.support.Rect;

public class Launcher extends ArcMount {
    long ammo, maxAmmo;
    Cooldown fireCooldown, reloadCooldown;
    public double getAmmoPercent() { return (double)ammo / (double)maxAmmo; }

    public Launcher() {
        maxArc = 0;
        maxAmmo = 1;
    }

    public void trigger(GameObject owner) {
        if (fireCooldown.trigger() && ammo > 0) {
            reloadCooldown.reset();
            fireOnce(owner);
        }
    }

    protected Projectile getProjectile() {
        return null;
    }

    protected void fireOnce(GameObject owner) {
        if (ammo > 0) {
            Projectile projectile = getProjectile();
            if (projectile != null) {
                Rect projectileBounds = projectile.getBounding();
                projectileBounds.copy(this.getBounding());
                projectileBounds.translate(0, -bounding.getHeight() / 2);
                projectile.fire(owner);
                ammo--;
            }
        }
    }

    public void update(long now) {
        super.update(now);
        fireCooldown.updateWait(now);
        if (reloadCooldown.updateTrigger(now) && ammo < maxAmmo) {
            ammo++;
        }
    }
}
