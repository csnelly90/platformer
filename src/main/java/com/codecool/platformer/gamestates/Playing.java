package com.codecool.platformer.gamestates;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.constants.SpriteSize;
import com.codecool.platformer.entities.Player;
import com.codecool.platformer.levels.LevelManager;
import com.codecool.platformer.main.Game;
import com.codecool.platformer.ui.PauseOverlay;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {
    private Player player;
    private LevelManager levelManager;
    private PauseOverlay pauseOverlay;
    private boolean paused = false;
    private int xLevelOffset;
    private int leftBorder = (int) (0.2 * GameProperties.WINDOW_WIDTH);
    private int rightBorder = (int) (0.8 * GameProperties.WINDOW_WIDTH);
    private int levelTileCountHorizontal = LoadSave.getLevelData()[0].length;
    private int maxTilesOffset = levelTileCountHorizontal - GameProperties.TILES_HORIZONTAL;
    private int maxLevelOffsetX = maxTilesOffset * GameProperties.TILE_SIZE;

    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(100 * GameProperties.SCALE, 100 * GameProperties.SCALE, (int) (SpriteSize.PLAYER.WIDTH * GameProperties.SCALE), (int) (SpriteSize.PLAYER.HEIGHT * GameProperties.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
        pauseOverlay = new PauseOverlay(this);
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
            checkBorderProximity();
        } else {
            pauseOverlay.update();
        }
    }

    private void checkBorderProximity() {
        int playerX = (int) player.getHitbox().x;
        int diff = playerX - xLevelOffset;

        if (diff > rightBorder) {
            xLevelOffset += diff - rightBorder;
        } else if (diff < leftBorder) {
            xLevelOffset += diff - leftBorder;
        }

        // prevent offset from overflowing level borders when player is too close to border
        if (xLevelOffset > maxLevelOffsetX)  {
            xLevelOffset = maxLevelOffsetX;
        } else if (xLevelOffset < 0) {
            xLevelOffset = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g, xLevelOffset);
        player.render(g, xLevelOffset);

        if (paused) pauseOverlay.draw(g);
    }

    public void unpauseGame() {
        paused = false;
    }

    public void mouseDragged(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseDragged(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (paused) {
            pauseOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseReleased(e);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (paused) {
            pauseOverlay.mouseMoved(e);
        }
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
            case KeyEvent.VK_ESCAPE:
                paused = !paused;
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

    public Player getPlayer() {
        return player;
    }
}
