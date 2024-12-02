package prototype.objects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import prototype.managers.GameManager;
import prototype.support.Bound;
import prototype.support.Camera;
import prototype.support.Rect;

import java.util.ArrayList;
import java.util.Random;

public class GameObject implements Bound {
    static protected ArrayList<GameObject> objectList = null;
    public static void setObjectList(ArrayList<GameObject> objectList) { GameObject.objectList = objectList; }

    protected static Random rand = new Random();

    public enum Team {
        PLAYER, ENEMY, WORLD, NONE
    }
    protected Team team;
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }

    protected long maxHealth, currentHealth;
    protected boolean isCollidable = true;
    protected boolean isVisible = true;
    protected Rect bounding;
    protected Rect collisionBounding;
    protected Image sprite = null;

    public Rect getBounding() { return bounding; }
    public void setCollidable(boolean isCollidable) { this.isCollidable = isCollidable; }

    public GameObject(Rect origin) {
        initialize();
        bounding.copy(origin);
    }

    public GameObject() {
        initialize();
    }

    private void initialize() {
        bounding = new Rect(0, 0, 0, 0);
        collisionBounding = new Rect(bounding);
        maxHealth = 100;
        currentHealth = maxHealth;
        team = Team.WORLD;
    }

    public void updateCollisionBounding() {
        collisionBounding.copy(bounding);
        collisionBounding.center();
        collisionBounding.inset(bounding.getHeight()*0.10);
    }

    public void update(long time) {
        updateCollisionBounding();
    }

    public boolean collidesWith(GameObject other) {
        if (this == other || other == null) return false;
        if (!this.isCollidable || !other.isCollidable) return false;
        if (this.team == other.team) return false;
        return this.collisionBounding.intersects(other.collisionBounding);
    }

    public void draw(GraphicsContext gc) {
        if (!isVisible) return;
        gc.save();

        gc.translate(bounding.getX() - Camera.getX(), bounding.getY() - Camera.getY());
        gc.rotate(bounding.getRotation());
        gc.scale(bounding.getScale(), bounding.getScale());

        if (sprite != null) {
            gc.drawImage(sprite, -bounding.getWidth()/2, -bounding.getHeight()/2, bounding.getWidth(), bounding.getHeight());
        } else {
            gc.setFill(Color.PINK);
            gc.fillRect(-bounding.getWidth()/2, -bounding.getHeight()/2, bounding.getWidth(), bounding.getHeight());
        }

        gc.restore();

//        gc.setFill(Color.HOTPINK);
//        gc.fillRect(collisionBounding.getX() - Camera.getX(), collisionBounding.getY() - Camera.getY(), collisionBounding.getWidth(), collisionBounding.getHeight());
    }

    public void takeHealing(long healing) {
        currentHealth += healing;
        if (currentHealth > maxHealth) currentHealth = maxHealth;
    }

    public void takeDamage(long damage) {
        if (maxHealth > 0) {
            currentHealth -= damage;
            if (currentHealth <= 0) {
                handleDestruction();
            }
        }
    }

    protected void handleCollision(GameObject object, double oldX, double oldY) {
        object.takeCollision(this);
    }

    public void takeCollision(GameObject instigator) {

    }

    protected void handleDestruction() {
        GameManager.getInstance().parkResource(this);
    }
}
