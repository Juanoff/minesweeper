package ru.shift.model;

public class GameScore {
    private int openedCellsCount;
    private int flaggedCellsCount;
    private final int safeCellsCount;

    public GameScore(int mineCount, int fieldWidth, int fieldHeight) {
        this.openedCellsCount = 0;
        this.flaggedCellsCount = 0;
        this.safeCellsCount = fieldWidth * fieldHeight - mineCount;
    }

    public void setOpenedCellsCount(int openedCellsCount) {
        this.openedCellsCount = openedCellsCount;
    }

    public int getOpenedCellsCount() {
        return openedCellsCount;
    }

    public void setFlaggedCellsCount(int flaggedCellsCount) {
        this.flaggedCellsCount = flaggedCellsCount;
    }

    public int getFlaggedCellsCount() {
        return flaggedCellsCount;
    }

    public void reset() {
        openedCellsCount = 0;
        flaggedCellsCount = 0;
    }

    public boolean isGameWon() {
        return openedCellsCount == safeCellsCount;
    }
}
