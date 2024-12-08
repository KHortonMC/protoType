package prototype.objects;

import javafx.scene.control.ProgressBar;
import prototype.Main;
import prototype.managers.GameManager;
import prototype.managers.ImageManager;
import prototype.support.Camera;
import prototype.support.Cooldown;
import prototype.support.Rect;
import prototype.weapons.Rifle;

public class AIMech extends Mecha {
    GameObject targetObject;
    ProgressBar healthBar;

    public AIMech() {
        super();
    }

    public AIMech(Rect bounding) {
        super(bounding);
    }

    @Override
    protected void initialize() {
        this.team = Team.ENEMY;
        super.initialize();
        bounding.set(0,0,100,100, 0, 1);

        animCooldown = new Cooldown(15,0);
        healCooldown = new Cooldown(15,120);

        String color = rand.nextInt(3) == 0 ? "red" : rand.nextInt(3) == 1 ? "green" : "blue";

        stand = ImageManager.getImage("mech"+color+"s");
        walk1 = ImageManager.getImage("mech"+color+"l");
        walk2 = ImageManager.getImage("mech"+color+"r");

        healthBar = new ProgressBar();
        healthBar.setProgress(getHealthPercent());
        maxHealth = 400;

        Main.getRoot().getChildren().add(healthBar);
        reset();
    }

    protected void reset() {
        healthBar.setVisible(true);
        currentHealth = maxHealth;
    }

    public void acquireTarget() {
        for (GameObject object : objectList) {
            if (object instanceof Player
                && object.getBounding().getDist(this.bounding) < 600) {
                targetObject = object;
            }
        }
    }

    @Override
    public void takeHealing(long healing) {
        super.takeHealing(healing);
        healthBar.setProgress(getHealthPercent());
    }

    @Override
    public void takeDamage(long damage) {
        super.takeDamage(damage);
        healthBar.setProgress(getHealthPercent());
    }

    @Override
    public void update(long now) {
        super.update(now);

        healthBar.setLayoutX(bounding.getX() - Camera.getX() - bounding.getWidth() * 0.5);
        healthBar.setLayoutY(bounding.getY() - Camera.getY() - bounding.getHeight() * 0.75);

        if (targetObject == null) {
            acquireTarget();
        } else if (bounding.getDist(targetObject.getBounding()) > 1000) {
            targetObject = null;
        } else {
            // rotate to face target
            double rotationDist = bounding.getRotationDist(targetObject.getBounding());
            double translationDist = bounding.getDist(targetObject.getBounding());

            if (rotationDist > 0) {
                deltaRotation = -1;
            } else if (rotationDist < 0) {
                deltaRotation = 1;
            }
            else {
                deltaRotation = 0;
            }

            if (Math.abs(rotationDist) < 45) {
                if (translationDist < 200) {
                    deltaY = 1;
                }
                else {
                    deltaY = -1;
                }
            }

            if (Math.abs(rotationDist) < 20) {
                fireWeapon(true);
            }
        }
    }

    @Override
    protected void handleDestruction() {
        super.handleDestruction();
        healthBar.setVisible(false);
    }

    @Override
    public void destruct() {
        super.destruct();
        Main.getRoot().getChildren().remove(healthBar);
        healthBar = null;
    }
}
