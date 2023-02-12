package com.codecool.platformer.constants;

public final class GameProperties {

    public static final int ANIMATION_PER_SEC = 8;
    public static final int FPS_PER_SEC = 120;
    public static final int UPS_PER_SEC = 200;
    public static final int DEFAULT_TILE_SIZE = 32;
    public static final float SCALE = 2.5f;
    public static final int TILES_HORIZONTAL = 26;
    public static final int TILES_VERTICAL = 14;
    public static final int TILE_SIZE = (int) (DEFAULT_TILE_SIZE * SCALE);
    public static final int WINDOW_WIDTH = TILE_SIZE * TILES_HORIZONTAL;
    public static final int WINDOW_HEIGHT = TILE_SIZE * TILES_VERTICAL;

    private GameProperties() {
    }

    public static class UI {
        public static class Buttons {
            public static final int MB_DEFAULT_WIDTH = 140;
            public static final int MB_DEFAULT_HEIGHT = 56;
            public static final int MB_WIDTH = (int) (MB_DEFAULT_WIDTH * SCALE);
            public static final int MB_HEIGHT  = (int) (MB_DEFAULT_HEIGHT * SCALE);
        }

        public static class PauseButtons {
            public static final int SOUND_BUTTON_DEFAULT_SIZE = 42;
            public static final int SOUND_BUTTON_SIZE = (int) (SOUND_BUTTON_DEFAULT_SIZE * SCALE);
            public static final int URM_BUTTON_DEFAULT_SIZE = 56;
            public static final int URM_BUTTON_SIZE = (int) (URM_BUTTON_DEFAULT_SIZE * SCALE);
            public static final int VOL_BUTTON_DEFAULT_WIDTH = 28;
            public static final int VOL_BUTTON_DEFAULT_HEIGHT = 44;
            public static final int VOL_SLIDER_DEFAULT_WIDTH = 215;
            public static final int VOL_BUTTON_WIDTH = (int) (VOL_BUTTON_DEFAULT_WIDTH * SCALE);
            public static final int VOL_BUTTON_HEIGHT = (int) (VOL_BUTTON_DEFAULT_HEIGHT * SCALE);
            public static final int VOL_SLIDER_WIDTH = (int) (VOL_SLIDER_DEFAULT_WIDTH * SCALE);
        }
    }
}