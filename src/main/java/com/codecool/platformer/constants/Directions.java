package com.codecool.platformer.constants;

public enum Directions {
    LEFT(0),
    UP(1),
    RIGHT(2),
    DOWN(3),
    NONE(-1);

    public final int VALUE;

    Directions(int value) {
        this.VALUE = value;
    }
}
