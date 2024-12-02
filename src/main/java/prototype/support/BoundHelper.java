package prototype.support;

public class BoundHelper implements Bound {
    public static BoundHelper helper = new BoundHelper();
    Rect bounding = new Rect();

    @Override
    public Rect getBounding() { return bounding; }
}
