package ru.shift.controller;

import ru.shift.model.GameModel;
import ru.shift.view.ButtonType;
import ru.shift.view.CellEventListener;

public class GameController implements CellEventListener {
    private final GameModel gameModel;

    public GameController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void onMouseClick(int x, int y, ButtonType buttonType) {
        switch (buttonType) {
            case LEFT_BUTTON -> gameModel.handleLeftClick(x, y);
            case RIGHT_BUTTON -> gameModel.handleRightClick(x, y);
            case MIDDLE_BUTTON -> gameModel.handleMiddleClick(x, y);
        }
    }

    public void restartGame() {
        gameModel.restartGame();
    }

    public void exitGame() {
        gameModel.exitGame();
    }
}
