package ru.shift.model.field.cell.state;

import ru.shift.model.field.cell.ICell;
import ru.shift.view.GameImage;

public class ClosedState implements CellState {
    @Override
    public GameImage getGameImage(ICell cell) {
        return GameImage.CLOSED;
    }

    @Override
    public boolean isOpened() {
        return false;
    }

    @Override
    public CellState open() {
        return new OpenedState();
    }

    @Override
    public CellState toggleFlag() {
        return new FlaggedState();
    }
}
