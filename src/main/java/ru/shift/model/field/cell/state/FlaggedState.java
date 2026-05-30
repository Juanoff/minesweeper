package ru.shift.model.field.cell.state;

import ru.shift.model.field.cell.ICell;
import ru.shift.view.GameImage;

public class FlaggedState implements CellState {
    @Override
    public GameImage getGameImage(ICell cell) {
        return GameImage.MARKED;
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public CellState open() {
        return this;
    }

    @Override
    public CellState toggleFlag() {
        return new ClosedState();
    }
}
