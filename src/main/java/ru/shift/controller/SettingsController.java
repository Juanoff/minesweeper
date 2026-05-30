package ru.shift.controller;

import ru.shift.model.GameModel;
import ru.shift.shared.GameType;
import ru.shift.view.GameTypeListener;

public class SettingsController implements GameTypeListener {
    private final GameModel gameModel;

    public SettingsController(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    @Override
    public void onGameTypeChanged(GameType gameType) {
        gameModel.restartGame(gameType);
    }
}
