package com.codecool.platformer.constants;

public enum PlayerAnimations {
    IDLE(0, 5),
    RUNNING(1, 6),
    JUMP(2, 3),
    FALLING(3, 1),
    GROUND(4, 2),
    HIT(5, 4),
    ATTACK(6, 3),
    ATTACK_JUMP_1(7, 3),
    ATTACK_JUMP_2(8, 3);

    public final int ROW_INDEX;
    public final int SPITE_AMOUNT;

    PlayerAnimations(int rowIndex, int spiteAmount) {
        this.ROW_INDEX = rowIndex;
        this.SPITE_AMOUNT = spiteAmount;
    }
}
