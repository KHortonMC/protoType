package prototype.objects;

import prototype.Main;
import prototype.managers.ImageManager;

public class Background extends GameObject {
    public Background() {
        sprite = ImageManager.getImage("background");
        this.maxHealth = 0;
        bounding.setWidth(Main.getCanvas().getWidth()*2);
        bounding.setHeight(Main.getCanvas().getHeight()*2);
        bounding.setX(Main.getCanvas().getWidth() / 2);
        bounding.setY(Main.getCanvas().getHeight() / 2);
        this.isCollidable = false;
    }
}
