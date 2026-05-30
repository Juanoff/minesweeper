package ru.shift.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import ru.shift.model.GameModelListener;
import ru.shift.model.field.cell.ICell;
import ru.shift.shared.GameType;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RecordsWindow extends JDialog implements GameModelListener {
    private RecordNameListener nameListener;

    public RecordsWindow(JFrame frame) {
        super(frame, "New Record", true);

        JTextField nameField = new JTextField();

        GridLayout layout = new GridLayout(3, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(nameField);
        contentPane.add(createOkButton(nameField));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 120));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void setNameListener(RecordNameListener nameListener) {
        this.nameListener = nameListener;
    }

    private JButton createOkButton(JTextField nameField) {
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            dispose();

            if (nameListener != null) {
                nameListener.onRecordNameEntered(nameField.getText());
            }
        });
        return button;
    }

    @Override
    public void onGameWin(GameType gameType) {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    @Override
    public void onCellOpened(int x, int y, ICell cell) {

    }

    @Override
    public void onCellFlagToggled(int x, int y, ICell cell) {

    }

    @Override
    public void onGameStart() {

    }

    @Override
    public void onGameRestart(GameType gameType) {

    }

    @Override
    public void onGameExit() {

    }

    @Override
    public void onGameLose() {

    }

    @Override
    public void onMinesLeftChanged(int minesLeft) {

    }
}
