package com.codecool.platformer.entities;

import com.codecool.platformer.constants.Directions;
import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.PlayerAnimations;
import com.codecool.platformer.constants.SpriteSize;
import com.codecool.platformer.utils.HelpMethods;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = GameProperties.FPS_PER_SEC / GameProperties.ANIMATION_PER_SEC;
    private PlayerAnimations playerAction = PlayerAnimations.IDLE;
    private Directions playerDirection = Directions.NONE;
    private float playerSpeed = 2.0f;
    private boolean moving, attacking = false;
    private boolean left, up, right, down, jump;
    private int[][] levelData;
    private float xDrawOffset = 21 * GameProperties.SCALE; // pixel difference between hitbox and player sprite size on x-axis
    private float yDrawOffset = 4 * GameProperties.SCALE; // pixel difference between hitbox and player sprite size on y-axis

    // Jumping, falling, gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * GameProperties.SCALE;
    private float jumpSpeed = -2.25f * GameProperties.SCALE;
    private float fallSpeedAfterCollision = 0.5f * GameProperties.SCALE;
    private boolean inAir = false;

    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(
                x,
                y,
                SpriteSize.PLAYER.HITBOX_WIDTH * GameProperties.SCALE,
                SpriteSize.PLAYER.HITBOX_HEIGHT * GameProperties.SCALE
        );
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
                (int) (hitbox.x - xDrawOffset),
                (int) (hitbox.y - yDrawOffset),
                width,
                height,
                null
        );
        drawHitbox(g);
    }

    private void updateAnimationTick() {
        animationTick++;

        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= playerAction.SPITE_AMOUNT) {
                animationIndex = 0;
                attacking = false;
            }
        }
    }

    private void resetAnimationTick() {
        animationTick = 0;
        animationIndex = 0;
    }

    private void updatePosition() {
        moving = false;

        if (jump) {
            jump();
        }

        if (!left && !right && !inAir) return;

        // temporary storage of x
        float xSpeed = 0;

        if (left) {
            xSpeed -= playerSpeed;
        }

        if (right) {
            xSpeed += playerSpeed;
        }

        if (!inAir) {
            if (!HelpMethods.isEntityOnFloor(hitbox, levelData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (HelpMethods.canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPosition(xSpeed);
            } else {
                hitbox.y = HelpMethods.getEntityYPositionUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                    updateXPosition(xSpeed);
                }
            }
        } else {
            updateXPosition(xSpeed);
        }

        moving = true;
    }

    private void jump() {
        if (inAir) return; // if already jumping

        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPosition(float xSpeed) {
        if (HelpMethods.canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = HelpMethods.getEntityXPositionNextToWall(hitbox, xSpeed);
        }
    }

    private void setAnimation() {
        int startAnimation = playerAction.ROW_INDEX;

        if (moving) {
            this.playerAction = PlayerAnimations.RUNNING;
        } else {
            this.playerAction = PlayerAnimations.IDLE;
        }

        if (inAir) {
            if (airSpeed < 0) { // going upwards
                this.playerAction = PlayerAnimations.JUMP;
            } else {
                this.playerAction = PlayerAnimations.FALLING;
            }
        } else {

        }

        if (attacking) {
            playerAction = PlayerAnimations.ATTACK;
        }

        // reset animation counters when animation is abrupt by another animation
        if (startAnimation != playerAction.ROW_INDEX) {
            resetAnimationTick();
        }
    }

    private void loadAnimations() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

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
    }

    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;

        // make player instantly fall to ground if in air at level start
        if (!HelpMethods.isEntityOnFloor(hitbox, levelData)) {
            inAir = true;
        }
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
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

    public void resetDirectionBooleans() {
        left = false;
        up = false;
        right = false;
        down = false;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
