package ru.shift.model.state;

import ru.shift.model.GameModel;

public class NotStartedState implements GameState {
    @Override
    public void handleLeftClick(int x, int y, GameModel gameModel) {
        gameModel.getField().createGameField(x, y);
        gameModel.setGameState(new PlayingState());
        gameModel.notifyGameStart();
        gameModel.handleLeftClick(x, y);
    }

    @Override
    public void handleRightClick(int x, int y, GameModel gameModel) {
        gameModel.toggleFlag(x, y);
    }

    @Override
    public void handleMiddleClick(int x, int y, GameModel gameModel) {
    }
}
