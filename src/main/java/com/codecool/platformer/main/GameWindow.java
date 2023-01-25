package com.codecool.platformer.main;

import javax.swing.*;

public class GameWindow {
    private JFrame frame;

    public GameWindow(GamePanel gamePanel) {
        this.frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(gamePanel);
        frame.setVisible(true);
    }
}
