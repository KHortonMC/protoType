package prototype.projectiles;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import prototype.Main;
import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.objects.GameObject;
import prototype.support.Rect;

public class Explosion extends GameObject {
    long damage;
    long time = 10;

    public Explosion() {
        sprite = ImageManager.newSprite("explosion");
        isCollidable = false;
        maxHealth = currentHealth = 0;
    }

    public void setRect(Rect impact) {
        bounding.copy(impact);
    }

    public void setSize(double size) {
        bounding.setWidth(size);
        bounding.setHeight(size);
        damage = (long)(size*0.5);
        time = 35;
    }

    @Override
    public void update(long now) {
        super.update(now);
        time -= 1;
        if (time <= 0) {
            handleDestruction();
        }
    }

    @Override
    public void handleDestruction() {
        super.handleDestruction();
        if (this.team != Team.NONE) {
            for (GameObject o : objectList) {
                if ((o.getTeam() != this.team || this.team == Team.WORLD)
                    && bounding.getDist(o.getBounding()) < (bounding.getWidth()*0.5)) {
                    o.takeDamage(this.damage);
                }
            }
        }
    }
}
