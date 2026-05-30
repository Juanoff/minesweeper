package ru.shift.timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import ru.shift.model.GameModelListener;
import ru.shift.model.field.cell.ICell;
import ru.shift.shared.GameType;

import javax.swing.Timer;

public class SwingGameTimer implements GameTimer, ActionListener, GameModelListener {
    private final Timer timer;
    private int currentTime;

    private final List<TimerListener> listeners = new ArrayList<>();

    public SwingGameTimer(int periodMillis) {
        this.currentTime = 0;
        this.timer = new Timer(periodMillis, this);
        timer.setInitialDelay(0);
    }

    @Override
    public void start() {
        currentTime = 0;
        timer.start();
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public int getCurrentTime() {
        return currentTime;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        currentTime++;
        notifyTimerTick();
    }

    public void addListener(TimerListener listener) {
        listeners.add(listener);
    }

    public void notifyTimerTick() {
        for (TimerListener listener : listeners) {
            listener.onTimerTick(getCurrentTime());
        }
    }

    @Override
    public void onGameStart() {
        start();
    }

    @Override
    public void onGameRestart(GameType gameType) {
        stop();
    }

    @Override
    public void onGameExit() {
        stop();
    }

    @Override
    public void onGameWin(GameType gameType) {
        stop();
    }

    @Override
    public void onGameLose() {
        stop();
    }

    @Override
    public void onCellOpened(int x, int y, ICell cell) {

    }

    @Override
    public void onCellFlagToggled(int x, int y, ICell cell) {

    }

    @Override
    public void onMinesLeftChanged(int minesLeft) {

    }
}
