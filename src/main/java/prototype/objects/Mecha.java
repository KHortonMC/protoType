package prototype.objects;

import javafx.scene.image.Image;
import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.managers.WeaponManager;
import prototype.projectiles.Explosion;
import prototype.support.BoundHelper;
import prototype.support.Cooldown;
import prototype.support.Rect;
import prototype.weapons.ArcMount;
import prototype.weapons.Hardpoint;

public class Mecha extends LocomotionObject {
    public final static int MAX_WEAPONS = 5;
    WeaponManager[] weapons = new WeaponManager[MAX_WEAPONS];
    public WeaponManager getWeapon(int index) { return weapons[index]; }

    double moveSpeed = 2;

    Image stand, walk1, walk2;

    Cooldown animCooldown;
    Cooldown healCooldown;

    public void setDeltaX(double deltaX) { this.deltaX = deltaX * moveSpeed; }
    public void setDeltaY(double deltaY) { this.deltaY = deltaY * moveSpeed; }

    public double getHealthPercent() { return (double)currentHealth / (double)maxHealth; }

    public Mecha() {
        super();
        initialize();
    }

    public Mecha(Rect bounds) {
        super(bounds);
        initialize();
    }

    protected void initialize() {
        sprite = stand;

        this.isVisible = true;

        for (int i = 0; i < MAX_WEAPONS; i++) {
            weapons[i] = new WeaponManager();
            weapons[i].setTeam(team);
        }

        Hardpoint leftArm = new Hardpoint();
        leftArm.offset(-50,-20);
        weapons[0].setHardpoint(leftArm);

        Hardpoint rightArm = new Hardpoint();
        rightArm.offset(50,-20);
        weapons[1].setHardpoint(rightArm);

        ArcMount leftShoulder = new ArcMount();
        leftShoulder.offset(-60,20);
        leftShoulder.setRotation(-90);
        leftShoulder.setArc(30);
        weapons[2].setHardpoint(leftShoulder);

        ArcMount rightShoulder = new ArcMount();
        rightShoulder.offset(60,20);
        rightShoulder.setRotation(90);
        rightShoulder.setArc(30);
        weapons[3].setHardpoint(rightShoulder);

        ArcMount headTurret = new ArcMount();
        headTurret.offset(0,30);
        headTurret.setRotation(0);
        headTurret.setArc(45);
        weapons[4].setHardpoint(headTurret);
    }

    public void pickupWeapon(Hardpoint weapon) {
        for (int i = 0; i < MAX_WEAPONS; i++) {
            if (!weapons[i].isEquipped()) {
                pickupWeapon(weapon, i);
                break;
            }
        }
    }

    public void pickupWeapon(Hardpoint weapon, int index) {
        if (index >= 0 && index < MAX_WEAPONS && weapon != null) {
            weapons[index].setWeapon(weapon);
            weapon.setCollidable(false);
        }
    }

    public void ejectSelected() {
        for (int i = 0; i < MAX_WEAPONS; i++) {
            if (weapons[i].isSelected() && weapons[i].isEquipped()) {
                dropWeapon(i);
            }
        }
    }

    public void dropWeapon(int index) {
        if (index >= 0 && index < MAX_WEAPONS && weapons[index] != null) {
            Hardpoint weapon = weapons[index].getHardpoint().getFirst();
            weapons[index].setWeapon(null);
            weapon.setTeam(GameObject.Team.WORLD);

            BoundHelper bh = BoundHelper.helper;
            bh.getBounding().copy(bounding);
            double degrees = rand.nextDouble(360);
            bh.getBounding().setRotation(degrees);
            bh.getBounding().translate(0,bounding.getHeight() * 1.5);
            bh.getBounding().setRotation(degrees+180);
            weapon.reposition(bh);
            weapon.setCollidable(true);
        }
    }

    public void toggleWeapon(int index) {
        WeaponManager weapon = weapons[index];
        weapon.getWeaponUI().toggleSelected();
    }

    boolean firingWeapons = false;
    public void fireWeapon(boolean value) {
        firingWeapons = value;
    }

    @Override
    protected void handleCollision(GameObject object, double oldX, double oldY) {
        super.handleCollision(object, oldX, oldY);
        if (object instanceof Hardpoint) {
            pickupWeapon((Hardpoint) object);
        }
    }

    @Override
    public void takeDamage(long damage) {
        super.takeDamage(damage);
        healCooldown.reset();
    }

    @Override
    public void update(long now) {
        super.update(now);

        bounding.setRotation(bounding.getRotation() + deltaRotation);

        if (firingWeapons) {
            for (int i = 0; i < MAX_WEAPONS; i++) {
                weapons[i].getHardpoint().trigger(this);
            }
        }

        if (animCooldown.updateTrigger(now)) {
            if (deltaX != 0 || deltaY != 0 || deltaRotation != 0) {
                sprite = walk1;
                walk1 = walk2;
                walk2 = sprite;
            } else {
                sprite = stand;
            }
        }

        if (currentHealth < maxHealth && healCooldown.updateTrigger(now)) {
            takeHealing(1);
        }

        for (int i = 0; i < MAX_WEAPONS; i++) {
            weapons[i].update(now, this);
        }
    }

    protected void handleDestruction() {
        super.handleDestruction();

        Explosion explosion = (Explosion) GameManager.getInstance().getResource(Explosion.class);
        explosion.setTeam(this.team);
        explosion.setRect(this.bounding);
        explosion.setSize(150);

        for (int i = 0; i < MAX_WEAPONS; i++) {
            if (weapons[i].isEquipped()) {
                dropWeapon(i);
            }
        }
    }
}
