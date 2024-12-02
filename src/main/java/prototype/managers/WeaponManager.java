package prototype.managers;

import prototype.controllers.WeaponUI;
import prototype.objects.GameObject;
import prototype.weapons.Hardpoint;
import prototype.weapons.Launcher;

public class WeaponManager {
    private WeaponUI weaponUI;
    private Hardpoint hardpoint;
    boolean isEquipped = false;
    GameObject.Team team;

    public WeaponUI getWeaponUI() { return weaponUI; }
    public Hardpoint getHardpoint() { return hardpoint; }
    public boolean isEquipped() { return isEquipped; }
    public boolean isSelected() {
        return weaponUI.isSelected();
    }

    public void setWeaponUI(WeaponUI weaponUI) { this.weaponUI = weaponUI; }
    public void setHardpoint(Hardpoint hardpoint) { this.hardpoint = hardpoint; }
    public void setTeam(GameObject.Team team) { this.team = team; }

    public WeaponManager() {}

    public Hardpoint getWeapon() {
        return this.hardpoint.getFirst();
    }

    public void setWeapon(Hardpoint weapon) {
        this.hardpoint.clearComponents();
        isEquipped = false;
        if (weaponUI != null) {
            weaponUI.setWeapon(null);
        }

        if (weapon != null) {
            this.hardpoint.addComponent(weapon);
            if (weaponUI != null) {
                weaponUI.setWeapon(weapon.getIconName());
            }
            weapon.setTeam(team);
            isEquipped = true;
        }
    }

    public void update(long now, GameObject owner) {
        hardpoint.update(now);
        hardpoint.reposition(owner);
        if (weaponUI != null && hardpoint.getFirst() instanceof Launcher) {
            weaponUI.setAmmo(((Launcher)hardpoint.getFirst()).getAmmoPercent());
        }
    }
}
