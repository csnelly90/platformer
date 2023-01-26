package com.codecool.platformer.constants;

public final class Properties {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 800;
    public static final int ANIMATION_PER_SEC = 8;
    public static final int FPS_PER_SEC = 120;

    private Properties() {
    }

    public enum SpriteSize {
        PLAYER(64, 40);
        public final int WIDTH;
        public final int HEIGHT;

        SpriteSize(int width, int height) {
            this.WIDTH = width;
            this.HEIGHT = height;
        }

        public enum PlayerAnimations {
            RUNNING(0, 6),
            IDLE(1,5),
            JUMP(2,3),
            FALLING(3,1),
            GROUND(4,2),
            HIT(5,4),
            ATTACK(6,3),
            ATTACK_JUMP_1(7,3),
            ATTACK_JUMP_2(8,3);

            public final int ROW_INDEX;
            public final int SPITE_AMOUNT;

            PlayerAnimations(int rowIndex, int spiteAmount) {
                this.ROW_INDEX = rowIndex;
                this.SPITE_AMOUNT = spiteAmount;
            }
        }
    }
}