package com.codecool.platformer.main;

import com.codecool.platformer.constants.Directions;
import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.PlayerAnimations;
import com.codecool.platformer.constants.SpriteSize;
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
    private final int PLAYER_WIDTH = SpriteSize.PLAYER.WIDTH;
    private final int PLAYER_HEIGHT = SpriteSize.PLAYER.HEIGHT;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = GameProperties.FPS_PER_SEC / GameProperties.ANIMATION_PER_SEC;
    private PlayerAnimations playerAction = PlayerAnimations.IDLE;
    private Directions playerDirection = Directions.NONE;
    private boolean moving = false;
    private final int STEP_SIZE = 5;

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
        this.animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++) {
            for (int j = 0; j < animations[i].length; j++) {
                animations[i][j] = img.getSubimage(j * PLAYER_WIDTH, i * PLAYER_HEIGHT, PLAYER_WIDTH, PLAYER_HEIGHT);
            }
        }
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.SPITE_AMOUNT) {
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
        Dimension size = new Dimension(GameProperties.WINDOW_WIDTH, GameProperties.WINDOW_HEIGHT);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void updateGame() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // enlarge sprite image to triple size
        g.drawImage(animations[playerAction.ROW_INDEX][animationIndex], (int) xDelta, (int) yDelta, PLAYER_WIDTH * 3, PLAYER_HEIGHT * 3, null);
    }

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    xDelta -= STEP_SIZE;
                    break;
                case UP:
                    yDelta -= STEP_SIZE;
                    break;
                case RIGHT:
                    xDelta += STEP_SIZE;
                    break;
                case DOWN:
                    yDelta += STEP_SIZE;
                    break;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            this.playerAction = PlayerAnimations.RUNNING;
        } else {
            this.playerAction = PlayerAnimations.IDLE;
        }
    }

    public void setDirection(Directions direction) {
        this.playerDirection = direction;
        this.moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
