package prototype.weapons;

import prototype.support.Bound;

public class ArcMount extends Hardpoint {
    double maxArc = 0;
    double arc = 0;
    double arcSpeed = 1;
    boolean arcDirection = true;

    public void setArc(double arc) {
        this.maxArc = arc;
    }

    public void reposition(Bound owner) {
        this.bounding.setX(owner.getBounding().getX());
        this.bounding.setY(owner.getBounding().getY());
        this.bounding.setRotation(owner.getBounding().getRotation());
        this.bounding.translate(deltaX, deltaY);

        scatter = rand.nextDouble() * maxScatter * 2;
        scatter -= maxScatter;

        if (maxArc > 0) {
                arc += arcDirection ? arcSpeed : -arcSpeed;
                if (Math.abs(arc) > maxArc) {
                    arcDirection = !arcDirection;
                }
        }

        this.bounding.setRotation(this.bounding.getRotation() + arc + baseRotation);
        this.scatterRect.copy(this.bounding);
        this.scatterRect.setRotation(this.scatterRect.getRotation() + scatter);
        for (Hardpoint hardpoint : children) {
            hardpoint.reposition(this);
        }
        updateCollisionBounding();
    }
}
