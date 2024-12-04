package prototype.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import prototype.managers.GameManager;
import javafx.scene.Group;
import prototype.Main;
import prototype.objects.Player;

public class GameController {
    @FXML
    private Canvas canvas;

    @FXML
    private Group group;

    @FXML
    private Label debugLabel;

    @FXML
    private HBox bottomHBox;

    @FXML
    private ProgressBar healthBar;

    private GraphicsContext gc;
    private GameManager gameManager;
    private PlayerController playerController;

    WeaponUI[] weaponUIs = new WeaponUI[Player.MAX_WEAPONS];

    AnimationTimer gameLoop = new AnimationTimer() {
        @Override
        public void handle(long now) {
            playerController.updatePlayer(gameManager.getPlayerObject());
            update(now);
            gameManager.update(now);
            gameManager.draw(gc);
        }
    };

    public void initialize() {
        Main.setDebugLabel(debugLabel);
        Main.setDebugText("");
        Main.setRoot(group);
        Main.setCanvas(canvas);

        gc = canvas.getGraphicsContext2D();
        canvas.requestFocus();
        playerController = new PlayerController();
        gameManager = new GameManager();

        for (int i = 0; i < Player.MAX_WEAPONS; i++) {
            int iconSize = 50;
            WeaponUI weaponUI = new WeaponUI();
            StackPane iconPane = new StackPane();
            iconPane.setMaxSize(iconSize,iconSize);

            ImageView iconView = new ImageView();
            iconView.setLayoutX(0);
            iconView.setLayoutY(0);
            iconView.setFitWidth(iconSize);
            iconView.setFitHeight(iconSize);
            iconPane.getChildren().add(iconView);

            ImageView iconFrame = new ImageView();
            iconFrame.setLayoutX(0);
            iconFrame.setLayoutY(0);
            iconFrame.setFitWidth(iconSize);
            iconFrame.setFitHeight(iconSize);
            iconPane.getChildren().add(iconFrame);

            ImageView ammoFrame = new ImageView();
            ammoFrame.setLayoutX(0);
            ammoFrame.setLayoutY(0);
            ammoFrame.setFitWidth(iconSize);
            ammoFrame.setFitHeight(iconSize);
            iconPane.getChildren().add(ammoFrame);
            iconPane.setAlignment(ammoFrame, Pos.TOP_CENTER);

            Label iconKey = new Label();
            iconKey.setText(String.valueOf(i+1));
            iconPane.getChildren().add(iconKey);
            iconPane.setAlignment(iconKey, Pos.BOTTOM_CENTER);

            weaponUI.setIconView(iconView);
            weaponUI.setFrameView(iconFrame);
            weaponUI.setAmmoView(ammoFrame);
            bottomHBox.getChildren().add(iconPane);
            weaponUIs[i] = weaponUI;
        }

        gameLoop.start();
        restart();
    }

    public void restart() {
        GameManager.getInstance().restart();

        for (int i = 0; i < Player.MAX_WEAPONS; i++) {
            weaponUIs[i].setWeapon(null);
            gameManager.getPlayerObject().getWeapon(i).setWeaponUI(weaponUIs[i]);
        }
    }

    public void shutdown() {
        gameLoop.stop();
        gameManager.shutdown();
    }

    public void update(long now) {
        healthBar.setProgress(GameManager.getInstance().getPlayerObject().getHealthPercent());
    }

    public void handleKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            restart();
        }
        playerController.handleKeyPressed(event);
    }

    public void handleKeyReleased(KeyEvent event) {

        playerController.handleKeyReleased(event);
    }
 }