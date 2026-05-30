package ru.shift.model.state;

import ru.shift.model.field.cell.ICell;
import ru.shift.model.GameModel;

public class PlayingState implements GameState {
    @Override
    public void handleLeftClick(int x, int y, GameModel gameModel) {
        ICell cell = gameModel.getField().getCell(x, y);
        if (cell.isOpened() || cell.isFlagged()) return;

        gameModel.openCell(x, y);

        if (cell.isMine()) {
            gameModel.setGameState(new LoseState());
            gameModel.revealAllMines();
            gameModel.notifyGameLose();
            return;
        }

        if (cell.getNeighboringMines() == 0) {
            gameModel.getField().getNeighboringCells(x, y).forEach(n -> gameModel.handleLeftClick(n.getX(), n.getY()));
        }

        if (gameModel.getScore().isGameWon()) {
            gameModel.setGameState(new WinState());
            gameModel.revealAllMines();
            gameModel.notifyGameWin();
        }
    }

    @Override
    public void handleRightClick(int x, int y, GameModel gameModel) {
        gameModel.toggleFlag(x, y);
    }

    @Override
    public void handleMiddleClick(int x, int y, GameModel gameModel) {
        ICell cell = gameModel.getField().getCell(x, y);
        if (!cell.isOpened() || cell.isMine() || cell.getNeighboringMines() == 0) {
            return;
        }

        int flaggedCount = gameModel.getField().countFlaggedNeighbors(x, y);

        if (flaggedCount == cell.getNeighboringMines()) {
            gameModel.getField().getNeighboringCells(x, y).forEach(neighbor -> {
                if (!neighbor.isFlagged() && !neighbor.isOpened()) {
                    gameModel.handleLeftClick(neighbor.getX(), neighbor.getY());
                }
            });
        }
    }
}
