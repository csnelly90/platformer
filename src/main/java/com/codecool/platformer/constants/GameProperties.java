package com.codecool.platformer.constants;

public final class GameProperties {

    public static final int ANIMATION_PER_SEC = 8;
    public static final int FPS_PER_SEC = 120;
    public static final int UPS_PER_SEC = 200;
    public static final int DEFAULT_TILE_SIZE = 32;
    public static final float SCALE = 1.5f;
    public static final int TILES_HORIZONTAL = 26;
    public static final int TILES_VERTICAL = 14;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int WINDOW_WIDTH = TILE_SIZE * TILES_HORIZONTAL;
    public static final int WINDOW_HEIGHT = TILE_SIZE * TILES_VERTICAL;

    private GameProperties() {
    }
}