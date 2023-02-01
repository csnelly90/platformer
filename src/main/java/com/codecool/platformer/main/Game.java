package com.codecool.platformer.main;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.constants.SpriteSize;
import com.codecool.platformer.entities.Player;
import com.codecool.platformer.levels.LevelManager;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int ONE_SEC_IN_MILLISECONDS = 1000;
    private final double ONE_SEC_IN_NANOSECONDS = 1000000000.0;
    private Player player;
    private LevelManager levelManager;


    public Game() {
        initClasses();
        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void initClasses() {
        levelManager = new LevelManager(this);
        player = new Player(200, 200, (int) (SpriteSize.PLAYER.WIDTH * GameProperties.SCALE), (int) (SpriteSize.PLAYER.HEIGHT * GameProperties.SCALE));
        player.loadLevelData(levelManager.getCurrentLevel().getLevelData());
    }

    private void startGameLoop() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        switch (Gamestate.state) {
            case MENU:
                // menu.update();
                break;
            case PLAYING:
                player.update();
                levelManager.update();
                break;
            default:
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                // menu.render(g);
                break;
            case PLAYING:
                levelManager.draw(g);
                player.render(g);
                break;
            default:
                break;
        }
    }

    @Override
    public void run() {
        double timePerFrame = ONE_SEC_IN_NANOSECONDS / GameProperties.FPS_PER_SEC;
        double timePerUpdate = ONE_SEC_IN_NANOSECONDS / GameProperties.UPS_PER_SEC;
        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        // delta times for updates and frames
        double deltaUpdate = 0;
        double deltaFrames = 0;

        while (true) {
            long currentTime = System.nanoTime();

            // make use of the time we lose when calculating FPS
            deltaUpdate += (currentTime - previousTime) / timePerUpdate;
            deltaFrames += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            // next update comes faster if we have more than 1 sec of lost time in total
            if (deltaUpdate >= 1) {
                update();
                updates++;
                deltaUpdate--;
            }

            if (deltaFrames >= 1) {
                gamePanel.repaint();
                frames++;
                deltaFrames--;
            }

            if (System.currentTimeMillis() - lastCheck >= ONE_SEC_IN_MILLISECONDS) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer() {
        return player;
    }

    public void windowFocusLost() {
        player.resetDirectionBooleans();
    }
}
