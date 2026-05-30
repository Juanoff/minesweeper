package ru.shift.model.field.cell;

import ru.shift.model.field.cell.state.CellState;
import ru.shift.model.field.cell.state.ClosedState;
import ru.shift.model.field.cell.state.FlaggedState;

public class Cell implements ICell {
    private CellState state = new ClosedState();
    private final int x;
    private final int y;
    private final boolean hasMine;
    private int neighboringMines = 0;

    public Cell(int x, int y, boolean hasMine) {
        this.x = x;
        this.y = y;
        this.hasMine = hasMine;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public CellState getState() {
        return state;
    }

    @Override
    public void setState(CellState state) {
        this.state = state;
    }

    @Override
    public void open() {
        if (!isOpened() && !isFlagged()) {
            setState(state.open());
        }
    }

    @Override
    public void toggleFlag() {
        if (!isOpened()) {
            setState(state.toggleFlag());
        }
    }

    @Override
    public boolean isMine() {
        return hasMine;
    }

    @Override
    public int getNeighboringMines() {
        return neighboringMines;
    }

    @Override
    public boolean isOpened() {
        return state.isOpened();
    }

    @Override
    public boolean isFlagged() {
        return state instanceof FlaggedState;
    }

    public void setNeighboringMines(int neighboringMines) {
        this.neighboringMines = neighboringMines;
    }
}
