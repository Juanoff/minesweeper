package ru.shift.model;

import ru.shift.model.field.cell.ICell;
import ru.shift.shared.GameType;

public interface GameModelListener {
    void onCellOpened(int x, int y, ICell cell);

    void onCellFlagToggled(int x, int y, ICell cell);

    void onGameStart();

    void onGameRestart(GameType gameType);

    void onGameExit();

    void onGameWin(GameType gameType);

    void onGameLose();

    void onMinesLeftChanged(int minesLeft);
}
