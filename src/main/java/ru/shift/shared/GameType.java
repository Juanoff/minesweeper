package ru.shift.shared;

public enum GameType {
    NOVICE("Novice", new GameSettings(10, 9, 9)),
    MEDIUM("Medium", new GameSettings(40, 16, 16)),
    EXPERT("Expert", new GameSettings(90, 16, 30)),
    ;

    private final String name;
    private final GameSettings settings;

    GameType(String name, GameSettings settings) {
        this.name = name;
        this.settings = settings;
    }

    public String getName() {
        return name;
    }

    public GameSettings getSettings() {
        return settings;
    }
}
