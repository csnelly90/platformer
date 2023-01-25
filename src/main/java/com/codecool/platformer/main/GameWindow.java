package com.codecool.platformer.main;

import javax.swing.*;

public class GameWindow {
    private JFrame frame;

    public GameWindow(GamePanel gamePanel) {
        this.frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
