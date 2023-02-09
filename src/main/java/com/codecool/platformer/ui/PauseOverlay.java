package com.codecool.platformer.ui;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PauseOverlay {
    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;

    public PauseOverlay() {
        loadBackground();
    }

    private void loadBackground() {
        background = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        menuWidth = (int) (background.getWidth() * GameProperties.SCALE);
        menuHeight = (int) (background.getHeight() * GameProperties.SCALE);
        menuX = GameProperties.WINDOW_WIDTH / 2 - menuWidth / 2;
        menuY = GameProperties.WINDOW_HEIGHT / 2 - menuHeight / 2;
    }

    public void update() {

    }

    public void draw(Graphics g) {
        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }
}
