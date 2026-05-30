package ru.shift.timer;

public interface GameTimer {
    void start();

    void stop();

    int getCurrentTime();

    void addListener(TimerListener listener);
}
