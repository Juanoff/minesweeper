package ru.shift.shared;

public record GameSettings(int mineCount, int fieldWidth, int fieldHeight) {
    public GameSettings {
        if (mineCount <= 0 || fieldWidth <= 0 || fieldHeight <= 0) {
            throw new IllegalArgumentException("Invalid game configuration");
        }
    }
}
