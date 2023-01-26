package com.codecool.platformer.entities;

import com.codecool.platformer.constants.Directions;
import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.PlayerAnimations;
import com.codecool.platformer.constants.SpriteSize;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = GameProperties.FPS_PER_SEC / GameProperties.ANIMATION_PER_SEC;
    private PlayerAnimations playerAction = PlayerAnimations.IDLE;
    private Directions playerDirection = Directions.NONE;
    private boolean moving = false;
    private final int STEP_SIZE = 5;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updateAnimationTick();
        setAnimation();
        updatePosition();
    }

    public void render(Graphics g) {
        // enlarge sprite image to triple size
        g.drawImage(
                animations[playerAction.ROW_INDEX][animationIndex],
                (int) x,
                (int) y,
                SpriteSize.PLAYER.WIDTH * 3,
                SpriteSize.PLAYER.HEIGHT * 3,
                null
        );
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

    private void updatePosition() {
        if (moving) {
            switch (playerDirection) {
                case LEFT:
                    x -= STEP_SIZE;
                    break;
                case UP:
                    y -= STEP_SIZE;
                    break;
                case RIGHT:
                    x += STEP_SIZE;
                    break;
                case DOWN:
                    y += STEP_SIZE;
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

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/sprites/player_sprites.png");

        try {
            BufferedImage img = ImageIO.read(is);
            this.animations = new BufferedImage[9][6];

            for (int i = 0; i < animations.length; i++) {
                for (int j = 0; j < animations[i].length; j++) {
                    animations[i][j] = img.getSubimage(
                            j * SpriteSize.PLAYER.WIDTH,
                            i * SpriteSize.PLAYER.HEIGHT,
                            SpriteSize.PLAYER.WIDTH,
                            SpriteSize.PLAYER.HEIGHT
                    );
                }
            }
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
}
