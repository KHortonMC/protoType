package prototype.support;

import prototype.Main;
import prototype.objects.GameObject;
import prototype.objects.Player;

public class Camera {
    static private GameObject player;
    public static void setPlayer(Player player) {
        Camera.player = player;
    }

    static double x, y;

    public static void update() {
        x = player.getBounding().getX() - player.getBounding().getWidth() / 2;
        y = player.getBounding().getY() - player.getBounding().getHeight() / 2;

        x -= Main.getCanvas().getWidth() / 2;
        y -= Main.getCanvas().getHeight() / 2;
    }

    public static double getX() { return x; }
    public static double getY() { return y; }
}
