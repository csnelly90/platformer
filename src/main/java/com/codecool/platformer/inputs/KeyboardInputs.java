package com.codecool.platformer.inputs;

import com.codecool.platformer.main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;
    private final int STEP_SIZE = 5;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-STEP_SIZE);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-STEP_SIZE);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(STEP_SIZE);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(STEP_SIZE);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
