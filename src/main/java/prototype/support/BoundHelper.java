package prototype.support;

public class BoundHelper implements IBound {
    public static BoundHelper helper = new BoundHelper();
    Rect bounding = new Rect();

    @Override
    public Rect getBounding() { return bounding; }
}
