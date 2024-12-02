package prototype.objects;

import prototype.support.Rect;

public class LocomotionObject extends GameObject {

    protected double deltaX, deltaY, deltaRotation;
    public void setDeltaX(double deltaX) { this.deltaX = deltaX; }
    public void setDeltaY(double deltaY) { this.deltaY = deltaY; }
    public void setDeltaRotation(double deltaRotation) { this.deltaRotation = deltaRotation; }

    public LocomotionObject() {
        super();
    }

    public LocomotionObject(Rect bounds) {
        super(bounds);
    }

    void move() {
        double oldX = bounding.getX();
        double oldY = bounding.getY();

        bounding.translate(deltaX, deltaY);
        updateCollisionBounding();

        for (GameObject object : objectList) {
            if (this.collidesWith(object)) {
                handleCollision(object, oldX, oldY);
            }
        }
    }

    @Override
    public void update(long now) {
        super.update(now);
        move();
    }

    protected void handleCollision(GameObject object, double oldX, double oldY) {
        bounding.setX(oldX);
        bounding.setY(oldY);
        updateCollisionBounding();
    }
}
