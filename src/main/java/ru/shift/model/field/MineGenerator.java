package ru.shift.model.field;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class MineGenerator {
    private static final RandomGenerator random = RandomGeneratorFactory.getDefault().create();
    private int mineCount;
    private int fieldWidth;
    private int fieldHeight;

    public void setMineCount(int mineCount) {
        this.mineCount = mineCount;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public int[][] generateMinesBySampling(int firstClickX, int firstClickY) {
        int[][] mines = new int[mineCount][2];
        boolean[][] used = new boolean[fieldHeight][fieldWidth];
        int generated = 0;

        while (generated < mineCount) {
            int x = random.nextInt(fieldWidth);
            int y = random.nextInt(fieldHeight);

            if (x == firstClickX && y == firstClickY) {
                continue;
            }

            if (!used[y][x]) {
                mines[generated][0] = x;
                mines[generated][1] = y;
                used[y][x] = true;
                generated++;
            }
        }
        return mines;
    }
}
