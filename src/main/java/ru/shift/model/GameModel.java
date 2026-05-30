package ru.shift.model;

import java.util.ArrayList;
import java.util.List;
import ru.shift.model.field.GameField;
import ru.shift.model.field.cell.ICell;
import ru.shift.model.state.GameState;
import ru.shift.model.state.NotStartedState;
import ru.shift.shared.GameSettings;
import ru.shift.shared.GameType;

public class GameModel {
    private GameType gameType;
    private GameState gameState;
    private GameField field;
    private GameScore score;

    private final List<GameModelListener> listeners = new ArrayList<>();

    public GameModel() {
        createGameModel(GameType.NOVICE);
    }

    private void createGameModel(GameType gameType) {
        this.gameType = gameType;
        GameSettings settings = gameType.getSettings();
        this.field = new GameField(settings.mineCount(), settings.fieldWidth(), settings.fieldHeight());
        this.score = new GameScore(settings.mineCount(), settings.fieldWidth(), settings.fieldHeight());
        this.gameState = new NotStartedState();
        this.score.reset();
    }

    public GameType getGameType() {
        return gameType;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameField getField() {
        return field;
    }

    public GameScore getScore() {
        return score;
    }

    public void addListener(GameModelListener listener) {
        listeners.add(listener);
    }

    public void notifyGameStart() {
        for (GameModelListener l : listeners) {
            l.onGameStart();
        }
    }

    public void restartGame() {
        restartGame(gameType);
    }

    public void restartGame(GameType gameType) {
        createGameModel(gameType);
        notifyGameRestart();
    }

    private void notifyGameRestart() {
        for (GameModelListener l : listeners) {
            l.onGameRestart(gameType);
        }
    }

    public void handleLeftClick(int x, int y) {
        gameState.handleLeftClick(x, y, this);
    }

    public void handleRightClick(int x, int y) {
        gameState.handleRightClick(x, y, this);
    }

    public void handleMiddleClick(int x, int y) {
        gameState.handleMiddleClick(x, y, this);
    }

    public void exitGame() {
        notifyGameExit();
        listeners.clear();
    }

    private void notifyGameExit() {
        for (GameModelListener l : listeners) {
            l.onGameExit();
        }
    }

    public void revealAllMines() {
        field.revealAllMines();

        for (ICell mine : field.getAllMines()) {
            int mx = mine.getX();
            int my = mine.getY();
            notifyCellOpened(mx, my, mine);
        }
    }

    public void openCell(int x, int y) {
        ICell cell = getField().getCell(x, y);
        if (cell.isOpened() || cell.isFlagged()) {
            return;
        }

        cell.open();

        score.setOpenedCellsCount(score.getOpenedCellsCount() + 1);

        notifyCellOpened(x, y, field.getCell(x, y));
    }

    private void notifyCellOpened(int x, int y, ICell cell) {
        for (GameModelListener listener : listeners) {
            listener.onCellOpened(x, y, cell);
        }
    }

    public void toggleFlag(int x, int y) {
        ICell cell = getField().getCell(x, y);
        if (cell.isOpened()) {
            return;
        }

        cell.toggleFlag();

        score.setFlaggedCellsCount(getScore().getFlaggedCellsCount() + (cell.isFlagged() ? 1 : -1));

        notifyToggleFlag(x, y, cell);
        notifyMinesLeftChanged();
    }

    private void notifyToggleFlag(int x, int y, ICell cell) {
        for (GameModelListener listener : listeners) {
            listener.onCellFlagToggled(x, y, cell);
        }
    }

    private void notifyMinesLeftChanged() {
        int minesLeft = gameType.getSettings().mineCount() - score.getFlaggedCellsCount();
        for (GameModelListener listener : listeners) {
            listener.onMinesLeftChanged(minesLeft);
        }
    }

    public void notifyGameLose() {
        for (GameModelListener listener : listeners) {
            listener.onGameLose();
        }
    }

    public void notifyGameWin() {
        for (GameModelListener listener : listeners) {
            listener.onGameWin(gameType);
        }
    }
}
