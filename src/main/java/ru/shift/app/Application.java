package ru.shift.app;

import ru.shift.controller.AppInitializer;
import ru.shift.controller.GameController;
import ru.shift.controller.RecordsController;
import ru.shift.controller.SettingsController;
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

public class Application {
    public static void main(String[] args) {
        SwingGameTimer gameTimer = new SwingGameTimer(1000);
        GameModel gameModel = new GameModel();
        RecordsService recordsService = new RecordsService();

        MainWindow mainWindow = new MainWindow();
        SettingsWindow settingsWindow = new SettingsWindow(mainWindow);
        HighScoresWindow highScoresWindow = new HighScoresWindow(mainWindow);
        RecordsWindow recordsWindow = new RecordsWindow(mainWindow);
        LoseWindow loseWindow = new LoseWindow(mainWindow);
        WinWindow winWindow = new WinWindow(mainWindow);
        AboutWindow aboutWindow = new AboutWindow(mainWindow);

        GameController gameController = new GameController(gameModel);
        SettingsController settingsController = new SettingsController(gameModel);
        RecordsController recordsController = new RecordsController(recordsService);

        AppInitializer appInitializer = new AppInitializer(
                gameModel, gameTimer, recordsService, mainWindow,
                settingsWindow, recordsWindow, highScoresWindow, loseWindow, winWindow, aboutWindow
        );

        mainWindow.setCellListener(gameController);
        mainWindow.setNewGameMenuAction(e -> gameController.restartGame());
        mainWindow.setExitMenuAction(e -> gameController.exitGame());
        settingsWindow.setGameTypeListener(settingsController);
        loseWindow.setNewGameListener(e -> gameController.restartGame());
        loseWindow.setExitListener(e -> gameController.exitGame());
        winWindow.setNewGameListener(e -> gameController.restartGame());
        winWindow.setExitListener(e -> gameController.exitGame());
        recordsWindow.setNameListener(recordsController);

        appInitializer.initialize();
        mainWindow.setVisible(true);
    }
}
