package prototype.managers;

import javafx.scene.canvas.GraphicsContext;
import prototype.Main;
import prototype.objects.Background;
import prototype.objects.GameObject;
import prototype.objects.Player;
import prototype.support.Camera;
import prototype.support.Rect;

import java.util.ArrayList;

public class GameManager {

    private final ArrayList<GameObject> objectsList;
    private final ArrayList<GameObject> addList;
    private final ArrayList<GameObject> removeList;
    private final ArrayList<GameObject> parkingList;
    private Player playerObject = null;
    public Player getPlayerObject() { return playerObject; }

    static GameManager instance = null;
    public static GameManager getInstance() { return instance; }

    public GameManager() {
        instance = this;
        objectsList = new ArrayList<>();
        addList = new ArrayList<>();
        removeList = new ArrayList<>();
        parkingList = new ArrayList<>();

        GameObject.setObjectList(objectsList);

        buildScene();
    }

    private void buildScene() {
        objectsList.clear();
        addList.clear();

        objectsList.add(new Background());

        playerObject = new Player(new Rect(300,300,100,100));
        Camera.setPlayer(playerObject);
        objectsList.add(playerObject);

        GameScene.buildScene();
    }

    public void update(long now) {
        for (GameObject object : objectsList) {
            object.update(now);
        }

        objectsList.removeAll(removeList);
        parkingList.addAll(removeList);
        removeList.clear();

        objectsList.addAll(addList);
        addList.clear();

        Camera.update();

        Main.setDebugText("Objects: " + objectsList.size() + " Parking: " + parkingList.size());
    }

    public void parkResource(GameObject object) {
        if (!removeList.contains(object)) {
            removeList.add(object);
        }
    }

    public GameObject getResource(Class<?> type) {
        GameObject object = getFromParking(type);
        if (object == null) {
            object = createNewResource(type);
        }
        if (object != null) {
            addList.add(object);
        }
        return object;
    }

    private GameObject getFromParking(Class<?> clazz) {
        GameObject object = null;
        for (GameObject o : parkingList) {
            if (o.getClass() == clazz) {
                object = o;
                break;
            }
        }

        if (object != null) {
            //System.out.println("Recycled Asset");
            parkingList.remove(object);
        }

        return object;
    }

    private GameObject createNewResource(Class<?> clazz) {
        GameObject object = null;
        try {
            object = (GameObject) clazz.getDeclaredConstructor().newInstance();
            //System.out.println("New Asset");
        } catch (Exception e) {
            System.out.println(e);
        }
        return object;
    }

    public void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        for (GameObject object : objectsList) {
            object.draw(gc);
        }
    }

    public void shutdown() {
        objectsList.clear();
        parkingList.clear();
        addList.clear();
        removeList.clear();
        playerObject = null;
    }
}
