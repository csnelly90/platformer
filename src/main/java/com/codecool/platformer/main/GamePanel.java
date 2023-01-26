package com.codecool.platformer.main;

import com.codecool.platformer.constants.ConstantProperties;
import com.codecool.platformer.inputs.KeyboardInputs;
import com.codecool.platformer.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private final int PLAYER_WIDTH = ConstantProperties.SpriteSize.PLAYER.WIDTH;
    private final int PLAYER_HEIGHT = ConstantProperties.SpriteSize.PLAYER.HEIGHT;
    private BufferedImage img;
    private BufferedImage[] idleAnimation;
    private int animationTick, animationIndex, animationSpeed = ConstantProperties.FPS_PER_SEC / ConstantProperties.ANIMATION_PER_SEC;

    public GamePanel() {
        this.mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        idleAnimation = new BufferedImage[5];

        for (int i = 0; i < idleAnimation.length; i++) {
            idleAnimation[i] = img.getSubimage(i * PLAYER_WIDTH, 0, PLAYER_WIDTH, PLAYER_HEIGHT);
        }
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= idleAnimation.length) {
                animationIndex = 0;
            }
        }
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/sprites/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateAnimationTick();
        g.drawImage(idleAnimation[animationIndex], (int) xDelta, (int) yDelta, PLAYER_WIDTH * 2, PLAYER_HEIGHT * 2, null);
    }

    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        yDelta += value;
        repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
}
