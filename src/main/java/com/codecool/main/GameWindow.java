package com.codecool.main;

import javax.swing.*;

public class GameWindow {
    private JFrame frame;

    public GameWindow() {
        this.frame = new JFrame();
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
