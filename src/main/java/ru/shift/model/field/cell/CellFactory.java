package ru.shift.model.field.cell;

public class CellFactory {
    public static ICell createEmptyCell(int x, int y) {
        return new Cell(x, y, false);
    }

    public static ICell createMineCell(int x, int y) {
        return new Cell(x, y, true);
    }
}
