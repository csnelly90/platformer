package com.codecool.platformer.main;

import com.codecool.platformer.inputs.KeyboardInputs;
import com.codecool.platformer.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;

    public GamePanel() {
        this.mouseInputs = new MouseInputs();
        addKeyListener(new KeyboardInputs());
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
