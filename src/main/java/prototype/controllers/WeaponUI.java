package prototype.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import prototype.managers.ImageManager;

public class WeaponUI {
    ImageView icon;
    ImageView frame;
    ImageView ammo;
    boolean isSelected;
    boolean isFilled;

    double ammoViewHeight;

    public void setIconView(ImageView icon) { this.icon = icon; }
    public void setFrameView(ImageView frame) { this.frame = frame; }
    public void setAmmoView(ImageView ammo)
    {
        this.ammo = ammo;
        ammoViewHeight = ammo.getBoundsInLocal().getHeight();
    }

    public WeaponUI() {}

    public void setWeapon(String type) {
        if (type == null) {
            icon.setImage(null);
            isSelected = false;
            isFilled = false;
        }
        else {
            Image iconFile = ImageManager.getImage("icon"+type);
            icon.setImage(iconFile);
            isFilled = true;
            isSelected = false;
        }
        setFrame();
    }

    public void toggleSelected() {
        if (isFilled) {
            setSelected(!isSelected);
        } else {
            setSelected(false);
        }
    }

    public boolean isSelected() { return isSelected; }

    public void setSelected(boolean selected) {
        isSelected = selected;
        setFrame();
    }

    public void setAmmo(double percent) {
        double fillHeight = ammoViewHeight * percent;
        if (fillHeight < 0.1) fillHeight = 0.1;
        ammo.setFitHeight(fillHeight);
    }

    private void setFrame() {
        String frameFile = "iconframeempty";
        if (isSelected) {
            frameFile = "iconframeselected";
        }
        else if (isFilled) {
            frameFile = "iconframefull";
        }
        frame.setImage(ImageManager.getImage(frameFile));
        ammo.setImage(ImageManager.getImage("iconframeammo"));
    }
}
