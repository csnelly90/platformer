package com.codecool.platformer.main;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.gamestates.Menu;
import com.codecool.platformer.gamestates.Playing;

import java.awt.*;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int ONE_SEC_IN_MILLISECONDS = 1000;
    private final double ONE_SEC_IN_NANOSECONDS = 1000000000.0;
    private Menu menu;
    private Playing playing;

    public Game() {
        initClasses();

        this.gamePanel = new GamePanel(this);
        this.gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    private void startGameLoop() {
        this.gameThread = new Thread(this);
        gameThread.start();
    }

    private void update() {
        switch (Gamestate.state) {
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
                break;
        }
    }

    public void render(Graphics g) {
        switch (Gamestate.state) {
            case MENU:
                menu.draw(g);
                break;
            case PLAYING:
                playing.draw(g);
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

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer().resetDirectionBooleans();
        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }
}
