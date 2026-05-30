package ru.shift.model.field.cell.state;

import ru.shift.model.field.cell.ICell;
import ru.shift.view.GameImage;

public interface CellState {
    GameImage getGameImage(ICell cell);

    boolean isOpened();

    CellState open();

    CellState toggleFlag();
}
