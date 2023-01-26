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
    private float playerSpeed = 2.0f;
    private boolean moving = false;
    private boolean left, up, right, down;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePosition();
        updateAnimationTick();
        setAnimation();
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
        moving = false;

        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else if (right && !left) {
            x += playerSpeed;
            moving = true;
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else if (down && !up) {
            y += playerSpeed;
            moving = true;
        }
    }

    private void setAnimation() {
        if (moving) {
            this.playerAction = PlayerAnimations.RUNNING;
        } else {
            this.playerAction = PlayerAnimations.IDLE;
        }
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

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}
