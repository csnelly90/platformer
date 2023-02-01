package com.codecool.platformer.utils;

import com.codecool.platformer.constants.GameProperties;

import java.awt.geom.Rectangle2D;

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

    public static float getEntityXPositionNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / GameProperties.TILE_SIZE);

        if (xSpeed > 0) { // wall is to the right
            int tileXPosition = currentTile * GameProperties.TILE_SIZE;
            int xOffset = (int) (GameProperties.TILE_SIZE - hitbox.width);

            // place player hitbox right next to wall on the right (-1 to avoid overlapping)
            return tileXPosition + xOffset - 1;
        } else { // wall is to the left
            return currentTile * GameProperties.TILE_SIZE;
        }
    }

    public static float getEntityYPositionUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / GameProperties.TILE_SIZE);

        if (airSpeed > 0) { // falling - touching floor
            int tileYPosition = currentTile * GameProperties.TILE_SIZE;
            int yOffset = (int) (GameProperties.TILE_SIZE - hitbox.height);
            return tileYPosition + yOffset - 1;
        } else { // jumping - touching roof
            return currentTile * GameProperties.TILE_SIZE;
        }
    }
}
