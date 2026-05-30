package ru.shift.model.field.cell;

import ru.shift.model.field.cell.state.CellState;

public interface ICell {
    int getX();

    int getY();

    CellState getState();

    void setState(CellState cellState);

    void open();

    void toggleFlag();

    boolean isMine();

    int getNeighboringMines();

    boolean isOpened();

    boolean isFlagged();
}
