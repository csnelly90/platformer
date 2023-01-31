package com.codecool.platformer.utils;

import com.codecool.platformer.constants.GameProperties;

public class HelpMethods {

    public static boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!isSolid(x, y, levelData)) {
            if (!isSolid(x + width, y + height, levelData)) {
                if (!isSolid(x + width, y, levelData)) {
                    if (!isSolid(x, y + height, levelData)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= GameProperties.WINDOW_WIDTH) return true;
        if (y < 0 || y >= GameProperties.WINDOW_HEIGHT) return true;

        float xIndex = x / GameProperties.TILE_SIZE;
        float yIndex = y / GameProperties.TILE_SIZE;
        int value = levelData[(int) yIndex][(int) xIndex];

        // 48 is the number of tiles on the level and 11 is the empty tile
        if (value >= 48 || value < 0 || value != 11) {
            return true;
        }

        return false;
    }

}
