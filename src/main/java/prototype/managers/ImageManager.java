package prototype.managers;

import javafx.scene.image.Image;
import prototype.Main;

import java.net.URL;
import java.util.Dictionary;
import java.util.Hashtable;

public class ImageManager {
    static Dictionary<String, Image> imageDictionary = new Hashtable<>();

    public static Image newSprite(String imageName) {
        URL imageURL = Main.class.getResource("/images/" + imageName + ".gif");
        if (imageURL != null) {
            return new Image(imageURL.toString());
        }
        return null;
    }

    public static Image getSprite(String imageName) {
        return ImageManager.getImage(imageName, ".gif");
    }

    public static Image getImage(String imageName) {
        return ImageManager.getImage(imageName, ".png");
    }

    public static Image getImage(String imageName, String extension) {
        Image image = imageDictionary.get(imageName);

        if (image == null) {
            URL imageURL = Main.class.getResource("/images/" + imageName + extension);
            if (imageURL != null) {
                image = new Image(imageURL.toString());
                imageDictionary.put(imageName, image);
            }
        }
        return image;
    }
}
