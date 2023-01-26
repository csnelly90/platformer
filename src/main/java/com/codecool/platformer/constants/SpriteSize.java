package com.codecool.platformer.constants;

public enum SpriteSize {
    PLAYER(64, 40);
    public final int WIDTH;
    public final int HEIGHT;

    SpriteSize(int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
    }
}
