package prototype.objects;

import prototype.Main;
import prototype.managers.ImageManager;

public class Background extends GameObject {
    public Background() {
        this.maxHealth = 0;
        bounding.setWidth(2000);
        bounding.setHeight(2000);
        bounding.setX(Main.getCanvas().getWidth() / 2);
        bounding.setY(Main.getCanvas().getHeight() / 2);
        this.isCollidable = false;
    }

    public void loadImage(String image) {
        sprite = ImageManager.getImage(image);
    }

    @Override
    public void updateCollisionBounding() {
        // do nothing, it's a background
    }
}
