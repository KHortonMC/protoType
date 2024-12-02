package prototype.controllers;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import prototype.objects.Player;

public class PlayerController {
    enum Actions {
        MoveUp,
        MoveDown,
        MoveLeft,
        MoveRight,
        RotateLeft,
        RotateRight,
        FireWeapons,
        EjectSelected,
        Select1,
        Select2,
        Select3,
        Select4,
        Select5,
    }

    boolean[] actionStates = new boolean[Actions.values().length];
    boolean[] weaponToggles = new boolean[Player.MAX_WEAPONS];

    public void updatePlayer(Player playerObject) {
        double deltaX = 0;
        double deltaY = 0;
        double deltaRotation = 0;

        deltaX += actionStates[Actions.MoveRight.ordinal()] ? 1 : 0;
        deltaX += actionStates[Actions.MoveLeft.ordinal()] ? -1 : 0;
        deltaY += actionStates[Actions.MoveUp.ordinal()] ? -1 : 0;
        deltaY += actionStates[Actions.MoveDown.ordinal()] ? 1 : 0;
        deltaRotation += actionStates[Actions.RotateLeft.ordinal()] ? -1 : 0;
        deltaRotation += actionStates[Actions.RotateRight.ordinal()] ? 1 : 0;

        playerObject.setDeltaX(deltaX);
        playerObject.setDeltaY(deltaY);
        playerObject.setDeltaRotation(deltaRotation);
        playerObject.fireWeapon(actionStates[Actions.FireWeapons.ordinal()]);

        if (actionStates[Actions.EjectSelected.ordinal()]) {
            actionStates[Actions.EjectSelected.ordinal()] = false;
            playerObject.ejectSelected();
        }

        for (int i = 0; i < weaponToggles.length; i++) {
            if (weaponToggles[i]) {
                playerObject.toggleWeapon(i);
                weaponToggles[i] = false;
            }
        }
    }

    public void handleKeyPressed(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> actionStates[Actions.MoveUp.ordinal()] = true;
            case S, DOWN -> actionStates[Actions.MoveDown.ordinal()] = true;
            case A -> actionStates[Actions.MoveLeft.ordinal()] = true;
            case D -> actionStates[Actions.MoveRight.ordinal()] = true;
            case LEFT -> actionStates[Actions.RotateLeft.ordinal()] = true;
            case RIGHT -> actionStates[Actions.RotateRight.ordinal()] = true;
            case SPACE -> actionStates[Actions.FireWeapons.ordinal()] = true;
            case X -> actionStates[Actions.EjectSelected.ordinal()] = true;
            case KeyCode.DIGIT1 -> weaponToggles[0] = true;
            case KeyCode.DIGIT2 -> weaponToggles[1] = true;
            case KeyCode.DIGIT3 -> weaponToggles[2] = true;
            case KeyCode.DIGIT4 -> weaponToggles[3] = true;
            case KeyCode.DIGIT5 -> weaponToggles[4] = true;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode()) {
            case W, UP -> actionStates[Actions.MoveUp.ordinal()] = false;
            case S, DOWN -> actionStates[Actions.MoveDown.ordinal()] = false;
            case A -> actionStates[Actions.MoveLeft.ordinal()] = false;
            case D -> actionStates[Actions.MoveRight.ordinal()] = false;
            case LEFT -> actionStates[Actions.RotateLeft.ordinal()] = false;
            case RIGHT -> actionStates[Actions.RotateRight.ordinal()] = false;
            case SPACE -> actionStates[Actions.FireWeapons.ordinal()] = false;
        }
    }
}
