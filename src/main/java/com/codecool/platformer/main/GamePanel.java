package com.codecool.platformer.main;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.inputs.KeyboardInputs;
import com.codecool.platformer.inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        this.mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GameProperties.WINDOW_WIDTH, GameProperties.WINDOW_HEIGHT);
        setPreferredSize(size);
    }

    public Game getGame() {
        return game;
    }
}
