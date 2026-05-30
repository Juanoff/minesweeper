package ru.shift.model.state;

import ru.shift.model.GameModel;

public interface GameState {
    void handleLeftClick(int x, int y, GameModel gameModel);

    void handleRightClick(int x, int y, GameModel gameModel);

    void handleMiddleClick(int x, int y, GameModel gameModel);
}
