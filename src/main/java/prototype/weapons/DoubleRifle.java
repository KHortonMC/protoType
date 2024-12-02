package prototype.weapons;

import prototype.managers.ImageManager;
import prototype.objects.GameObject;
import prototype.support.Cooldown;

public class DoubleRifle extends Launcher {
    Rifle left;
    Rifle right;

    boolean active = false;

    public DoubleRifle() {
        left = new Rifle();
        left.offset(-5,0);
        right = new Rifle();
        right.offset(5,0);
        children.add(left);
        children.add(right);
        fireCooldown = new Cooldown(15,0);
        reloadCooldown = new Cooldown(20,60);
        ammo = maxAmmo = 20;
        this.iconName = "doublerifle";
        sprite = ImageManager.getImage(this.iconName);
        this.bounding.setWidth(80);
        this.bounding.setHeight(80);
    }

    protected void fireOnce(GameObject owner) {
        fireCooldown.trigger();

        if (active) {
            left.fireOnce(owner);
            left.reloadCooldown.reset();
        } else {
            right.fireOnce(owner);
            right.reloadCooldown.reset();
        }
        active = !active;

        this.ammo = left.ammo + right.ammo;
    }
}
