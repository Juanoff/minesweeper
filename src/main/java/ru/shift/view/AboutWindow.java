package ru.shift.view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutWindow extends JDialog {
    public AboutWindow(JFrame owner) {
        super(owner, "About", true);

        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(createDescriptionLabel(layout));
        contentPane.add(createOkButton(layout));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(420, 310));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createDescriptionLabel(GridBagLayout layout) {
        String[] descriptionLines = {
                "\"Сапёр\" — это компьютерная игра-головоломка,",
                "в которой необходимо найти все мины на игровом поле,",
                "используя числовые подсказки.",
                "",
                "Как играть?",
                "1. Открывайте ячейки левой кнопкой мыши",
                "2. Если видите число - это количество мин вокруг",
                "3. Помечайте мины правой кнопкой мыши",
                "4. Колесико автоматически открывает соседние",
                "ячейки при правильной расстановке флагов",
                "5. Откройте всё поле, не наткнувшись на мину"
        };

        JPanel panel = new JPanel(new GridLayout(descriptionLines.length, 1));
        for (String line : descriptionLines) {
            JLabel label = new JLabel(line, JLabel.LEFT);
            panel.add(label);
        }

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 20, 5, 20);
        layout.setConstraints(panel, gbc);

        return panel;
    }

    private JButton createOkButton(GridBagLayout layout) {
        JButton okButton = new JButton("OK");
        okButton.setPreferredSize(new Dimension(100, 25));
        okButton.addActionListener(e -> dispose());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(15, 0, 15, 0);
        layout.setConstraints(okButton, gbc);

        return okButton;
    }
}
