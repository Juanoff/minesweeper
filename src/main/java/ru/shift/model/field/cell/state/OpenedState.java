package ru.shift.model.field.cell.state;

import ru.shift.model.field.cell.ICell;
import ru.shift.view.GameImage;

public class OpenedState implements CellState {
    @Override
    public GameImage getGameImage(ICell cell) {
        if (cell.isMine()) {
            return GameImage.BOMB;
        }
        return GameImage.getImageByNumber(cell.getNeighboringMines());
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public CellState open() {
        return this;
    }

    @Override
    public CellState toggleFlag() {
        return this;
    }
}
