package com.codecool.platformer.constants;

public final class ConstantProperties {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 800;
    public static final int ANIMATION_PER_SEC = 8;
    public static final int FPS_PER_SEC = 120;

    private ConstantProperties() {}

    public enum SpriteSize {
        PLAYER(64, 40);
        public final int WIDTH;
        public final int HEIGHT;

        SpriteSize(int width, int height) {
            this.WIDTH = width;
            this.HEIGHT = height;
        }
    }
}