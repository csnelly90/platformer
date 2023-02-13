package com.codecool.platformer.levels;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.main.Game;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelSprites;
    private Level levelOne;

    public LevelManager(Game game) {
        this.game = game;
        loadLevelSprites();
        levelOne = new Level(LoadSave.getLevelData());
    }

    private void loadLevelSprites() {
        BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.FIRST_LEVEL_ATLAS);
        levelSprites = new BufferedImage[48]; // 12 x 4 tiles

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                int index = i * 12 + j;
                levelSprites[index] = img.getSubimage(
                        j * GameProperties.DEFAULT_TILE_SIZE,
                        i * GameProperties.DEFAULT_TILE_SIZE,
                        GameProperties.DEFAULT_TILE_SIZE,
                        GameProperties.DEFAULT_TILE_SIZE
                );
            }
        }
    }

    public void draw(Graphics g, int levelOffset) {

        for (int i = 0; i < GameProperties.TILES_VERTICAL; i++) {
            for (int j = 0; j < levelOne.getLevelData()[0].length; j++) {
                int index = levelOne.getSpriteIndex(j, i);
                g.drawImage(
                        levelSprites[index],
                        GameProperties.TILE_SIZE * j - levelOffset,
                        GameProperties.TILE_SIZE * i,
                        GameProperties.TILE_SIZE,
                        GameProperties.TILE_SIZE,
                        null
                );
            }
        }

    }

    public void update() {

    }

    public Level getCurrentLevel() {
        return levelOne;

    }
}
