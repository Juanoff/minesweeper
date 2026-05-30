package ru.shift.controller;

import ru.shift.model.GameModel;
import ru.shift.records.RecordsService;
import ru.shift.timer.SwingGameTimer;
import ru.shift.view.AboutWindow;
import ru.shift.view.HighScoresWindow;
import ru.shift.view.LoseWindow;
import ru.shift.view.MainWindow;
import ru.shift.view.RecordsWindow;
import ru.shift.view.SettingsWindow;
import ru.shift.view.WinWindow;

public class AppInitializer {
    private final GameModel gameModel;
    private final SwingGameTimer gameTimer;
    private final RecordsService recordsService;
    private final MainWindow mainWindow;
    private final SettingsWindow settingsWindow;
    private final RecordsWindow recordsWindow;
    private final HighScoresWindow highScoresWindow;
    private final LoseWindow loseWindow;
    private final WinWindow winWindow;
    private final AboutWindow aboutWindow;

    public AppInitializer(
            GameModel gameModel,
            SwingGameTimer gameTimer,
            RecordsService recordsService,
            MainWindow mainWindow,
            SettingsWindow settingsWindow,
            RecordsWindow recordsWindow,
            HighScoresWindow highScoresWindow,
            LoseWindow loseWindow,
            WinWindow winWindow,
            AboutWindow aboutWindow
    ) {
        this.gameModel = gameModel;
        this.gameTimer = gameTimer;
        this.recordsService = recordsService;
        this.mainWindow = mainWindow;
        this.settingsWindow = settingsWindow;
        this.recordsWindow = recordsWindow;
        this.highScoresWindow = highScoresWindow;
        this.loseWindow = loseWindow;
        this.winWindow = winWindow;
        this.aboutWindow = aboutWindow;
    }

    public void initialize() {
        mainWindow.setSettingsMenuAction(e -> showSettings());
        mainWindow.setHighScoresMenuAction(e -> showHighScores());
        mainWindow.setAboutMenuAction(e -> showAbout());

        gameModel.addListener(mainWindow);
        gameModel.addListener(loseWindow);
        gameModel.addListener(winWindow);
        gameModel.addListener(recordsWindow);
        gameModel.addListener(gameTimer);
        gameModel.addListener(recordsService);

        gameTimer.addListener(mainWindow);
        gameTimer.addListener(recordsService);

        recordsService.addListener(highScoresWindow);
        recordsService.load();
    }

    private void showSettings() {
        settingsWindow.setVisible(true);
    }

    private void showHighScores() {
        highScoresWindow.setVisible(true);
    }

    private void showAbout() {
        aboutWindow.setVisible(true);
    }
}
