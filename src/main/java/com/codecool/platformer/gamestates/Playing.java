package com.codecool.platformer.gamestates;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.SpriteSize;
import com.codecool.platformer.entities.Player;
import com.codecool.platformer.levels.LevelManager;
import com.codecool.platformer.main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200, 200, (int) (SpriteSize.PLAYER.WIDTH * GameProperties.SCALE), (int) (SpriteSize.PLAYER.HEIGHT * GameProperties.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;
        }
    }
}
