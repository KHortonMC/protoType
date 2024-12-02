package prototype.weapons;

import prototype.objects.GameObject;
import prototype.support.Bound;
import prototype.support.Rect;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Hardpoint extends GameObject {
    protected ArrayList<Hardpoint> children = new ArrayList<>();

    double deltaX = 0;
    double deltaY = 0;

    protected double scatter, maxScatter;
    protected Rect scatterRect = new Rect();
    public Rect getBounding() { return scatterRect; }

    protected String iconName = null;
    public String getIconName() { return iconName; }

    double baseRotation;
    public void setRotation(double rotation) { this.baseRotation = rotation; }

    public Hardpoint() {
        super();
        maxHealth = 0;
    }

    public void offset(double x, double y) {
        deltaX = x;
        deltaY = y;
    }

    public Hardpoint getFirst() {
        if (children.size() > 0) {
            return children.get(0);
        }
        return null;
    }

    public void clearComponents() {
        children.clear();
    }

    public void addComponent(Hardpoint hardpoint) {
        children.add(hardpoint);
    }

    public void trigger(GameObject owner) {
        for (Hardpoint hardpoint : children) {
            hardpoint.trigger(owner);
        }
    }

    public void reposition(Bound owner) {
        this.bounding.setX(owner.getBounding().getX());
        this.bounding.setY(owner.getBounding().getY());
        this.bounding.setRotation(owner.getBounding().getRotation());
        this.bounding.translate(deltaX, deltaY);
        scatter = rand.nextDouble() * maxScatter * 2;
        scatter -= maxScatter;
        this.bounding.setRotation(owner.getBounding().getRotation() + baseRotation);
        this.scatterRect.copy(this.bounding);
        this.scatterRect.setRotation(this.bounding.getRotation() + scatter);
        for (Hardpoint hardpoint : children) {
            hardpoint.reposition(this);
        }
        updateCollisionBounding();
    }

    public void update(long now) {
        super.update(now);
        for (Hardpoint hardpoint : children) {
            hardpoint.update(now);
        }
    }
}
