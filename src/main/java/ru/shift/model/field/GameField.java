package ru.shift.model.field;

import java.util.ArrayList;
import java.util.List;
import ru.shift.model.field.cell.CellFactory;
import ru.shift.model.field.cell.ICell;
import ru.shift.model.field.cell.Cell;

public class GameField {
    private final MineGenerator mineGenerator = new MineGenerator();
    private final int mineCount;
    private final int width;
    private final int height;
    private final ICell[][] cells;
    private int[][] mineCoordinates;

    public GameField(int mineCount, int width, int height) {
        this.mineCount = mineCount;
        this.width = width;
        this.height = height;
        this.mineGenerator.setMineCount(mineCount);
        this.mineGenerator.setFieldWidth(width);
        this.mineGenerator.setFieldHeight(height);
        this.cells = new ICell[height][width];
        initEmptyCells();
    }

    private void initEmptyCells() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                cells[y][x] = CellFactory.createEmptyCell(x, y);
            }
        }
    }

    public ICell getCell(int x, int y) {
        return cells[y][x];
    }

    public void createGameField(int firstClickX, int firstClickY) {
        generateMines(firstClickX, firstClickY);
        calculateNeighbourCounts();
    }

    private void generateMines(int clickX, int clickY) {
        mineCoordinates = mineGenerator.generateMinesBySampling(clickX, clickY);
        for (int[] mineIndexes : mineCoordinates) {
            int x = mineIndexes[0];
            int y = mineIndexes[1];
            cells[y][x] = CellFactory.createMineCell(x, y);
        }
    }

    private void calculateNeighbourCounts() {
        for (int[] minesIndex : mineCoordinates) {
            int mx = minesIndex[0];
            int my = minesIndex[1];
            incrementNeighbourCounts(mx, my);
        }
    }

    private void incrementNeighbourCounts(int x, int y) {
        getNeighboringCells(x, y).stream()
                .filter(cell -> !cell.isMine())
                .forEach(cell -> {
                    Cell typedCell = (Cell) cell;
                    typedCell.setNeighboringMines(typedCell.getNeighboringMines() + 1);
                });
    }

    public void revealAllMines() {
        for (int[] mineCoordinate : mineCoordinates) {
            int mx = mineCoordinate[0];
            int my = mineCoordinate[1];

            ICell mine = cells[my][mx];
            mine.open();
        }
    }

    public ICell[] getAllMines() {
        ICell[] mines = new ICell[mineCount];
        int i = 0;
        for (int[] coordinate : mineCoordinates) {
            int mx = coordinate[0];
            int my = coordinate[1];
            mines[i++] = cells[my][mx];
        }
        return mines;
    }

    public List<ICell> getNeighboringCells(int centerX, int centerY) {
        List<ICell> neighbors = new ArrayList<>(8);

        int startY = Math.max(0, centerY - 1);
        int endY = Math.min(height - 1, centerY + 1);
        int startX = Math.max(0, centerX - 1);
        int endX = Math.min(width - 1, centerX + 1);

        for (int y = startY; y <= endY; y++) {
            for (int x = startX; x <= endX; x++) {
                if (y == centerY && x == centerX) {
                    continue;
                }
                neighbors.add(cells[y][x]);
            }
        }

        return neighbors;
    }

    public int countFlaggedNeighbors(int x, int y) {
        return (int) getNeighboringCells(x, y)
                .stream()
                .filter(ICell::isFlagged)
                .count();
    }
}
