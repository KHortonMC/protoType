package prototype;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    static Label debugLabel;
    static public void setDebugLabel(Label label) { debugLabel = label; }
    static public void setDebugText(String text) { debugLabel.setText(text); }

    static Group group;
    static public Group getRoot() { return group; }
    static public void setRoot(Group root) { group = root; }

    static Canvas canvas;
    static public void setCanvas(Canvas canvas) { Main.canvas = canvas; }
    static public Canvas getCanvas() { return canvas; }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);

        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setWidth(newValue.doubleValue());
        });

        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            canvas.setHeight(newValue.doubleValue());
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}