package ru.shift.view;

import javax.swing.ImageIcon;

public enum GameImage {
    CLOSED("images/cell.png"),
    MARKED("images/flag.png"),
    EMPTY("images/empty.png"),
    NUM_1("images/1.png"),
    NUM_2("images/2.png"),
    NUM_3("images/3.png"),
    NUM_4("images/4.png"),
    NUM_5("images/5.png"),
    NUM_6("images/6.png"),
    NUM_7("images/7.png"),
    NUM_8("images/8.png"),
    BOMB("images/mine.png"),
    TIMER("images/timer.png"),
    BOMB_ICON("images/mineImage.png"),
    ;

    private final String fileName;
    private ImageIcon imageIcon;

    GameImage(String fileName) {
        this.fileName = fileName;
    }

    public synchronized ImageIcon getImageIcon() {
        if (imageIcon == null) {
            imageIcon = new ImageIcon(ClassLoader.getSystemResource(fileName));
        }

        return imageIcon;
    }

    public static GameImage getImageByNumber(int number) {
        return switch (number) {
            case 1 -> GameImage.NUM_1;
            case 2 -> GameImage.NUM_2;
            case 3 -> GameImage.NUM_3;
            case 4 -> GameImage.NUM_4;
            case 5 -> GameImage.NUM_5;
            case 6 -> GameImage.NUM_6;
            case 7 -> GameImage.NUM_7;
            case 8 -> GameImage.NUM_8;
            default -> GameImage.EMPTY;
        };
    }
}
