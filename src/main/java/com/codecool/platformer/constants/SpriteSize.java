package com.codecool.platformer.constants;

public enum SpriteSize {
    PLAYER(64, 40, 20, 28);
    public final int WIDTH;
    public final int HEIGHT;
    public final int HITBOX_WIDTH;
    public final int HITBOX_HEIGHT;

    SpriteSize(int width, int height, int hitboxWidth, int hitboxHeight) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.HITBOX_WIDTH = hitboxWidth;
        this.HITBOX_HEIGHT = hitboxHeight;
    }
}
