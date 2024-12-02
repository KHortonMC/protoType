package prototype.support;

import prototype.Main;

public class Rect {
    double x, y, width, height, rotation, scale;

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getRotation() { return rotation; }
    public double getScale() { return scale; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setWidth(double width) { this.width = width; }
    public void setHeight(double height) { this.height = height; }
    public void setRotation(double rotation) { this.rotation = rotation; }
    public void setScale(double scale) { this.scale = scale; }

    public Rect(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = 0;
        this.scale = 1;
    }

    public Rect(Rect copy) {
        copy(copy);
    }

    public Rect() {
        this.x = 0;
        this.y = 0;
        this.width = 0;
        this.height = 0;
        this.rotation = 0;
        this.scale = 1;
    }

    public void copy(Rect copy) {
        this.x = copy.x;
        this.y = copy.y;
        this.width = copy.width;
        this.height = copy.height;
        this.rotation = copy.rotation;
        this.scale = copy.scale;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public void set(double x, double y, double width, double height) {
        setPosition(x, y);
        setSize(width, height);
    }

    public void set(double x, double y, double width, double height, double rotation, double scale) {
        set(x, y, width, height);
        this.rotation = rotation;
        this.scale = scale;
    }

    public void center() {
        x -= width * 0.5;
        y -= height * 0.5;
    }

    public void inset(double amount) {
        x += amount;
        y += amount;
        width -= amount*2;
        height -= amount*2;
    }

    public void translate(double x, double y) {
        double angle = Math.toRadians(rotation);
        double rotatedX = x * Math.cos(angle) - y * Math.sin(angle);
        double rotatedY = x * Math.sin(angle) + y * Math.cos(angle);

        this.x += rotatedX;
        this.y += rotatedY;
    }

    public boolean intersects(Rect other) {
        if (this == other || other == null) { return false; }
        return x <= other.x + other.width
                && x + width >= other.x
                && y <= other.y + other.height
                && y + height >= other.y;
    }

    public double getDist(Rect other) {
        double distX = x - other.x;
        double distY = y - other.y;
        return Math.sqrt(distX * distX + distY * distY);
    }

    private double normalizeAngle(double angle) {
        while (angle > Math.PI) angle -= 2 * Math.PI;
        while (angle < -Math.PI) angle += 2 * Math.PI;
        return angle;
    }

    public double getRotationDist(Rect other) {
        double targetAngle = Math.atan((other.y - this.y) / (other.x - this.x));
        if (other.x < this.x) {
            targetAngle += Math.PI;
        }

        targetAngle = normalizeAngle(targetAngle);
        targetAngle = Math.toDegrees(targetAngle);

        targetAngle += 90;

        //Main.setDebugText("current angle: " + rotation + " target angle: " + targetAngle);

        return rotation - targetAngle;
    }

    public enum Side {
        TOP, BOTTOM, LEFT, RIGHT
    }

    public Side intersectionSide(Rect other) {
        double closest = Double.MAX_VALUE;
        Side closestSide = null;
        double distance;

        distance = Math.abs(x - (other.x + other.width));
        if (distance < closest) {
            closest = distance;
            closestSide = Side.LEFT;
        }
        distance = Math.abs(y - (other.y + other.height));
        if (distance < closest) {
            closest = distance;
            closestSide = Side.TOP;
        }
        distance = Math.abs((x + width) - other.x);
        if (distance < closest) {
            closest = distance;
            closestSide = Side.RIGHT;
        }
        distance = Math.abs((y + height) - other.y);
        if (distance < closest) {
            closest = distance;
            closestSide = Side.BOTTOM;
        }
        return closestSide;
    }

    public String toString() {
        return "(" + x + "," + y + "," + width + "," + height + ")";
    }
}
