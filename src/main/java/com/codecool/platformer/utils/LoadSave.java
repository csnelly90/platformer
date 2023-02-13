package com.codecool.platformer.utils;

import com.codecool.platformer.constants.GameProperties;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/sprites/player_sprites.png";
    public static final String FIRST_LEVEL_ATLAS = "/tiles/rock_grass_terrain.png";
    // public static final String LEVEL_ONE_DATA = "/tiles/level_one_data.png";
    public static final String LEVEL_ONE_DATA = "/tiles/level_one_data_long.png";
    public static final String MENU_BUTTONS = "/gui/menu_buttons.png";
    public static final String MENU_BACKGROUND = "/gui/menu_background.png";
    public static final String PAUSE_BACKGROUND = "/gui/pause_menu.png";
    public static final String SOUND_BUTTONS = "/gui/sound_buttons.png";
    public static final String URM_BUTTONS = "/gui/urm_buttons.png";
    public static final String VOLUME_BUTTONS = "/gui/volume_buttons.png";

    public static BufferedImage getSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream(fileName);

        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static int[][] getLevelData() {
        BufferedImage img = getSpriteAtlas(LEVEL_ONE_DATA);
        int[][] levelData = new int[img.getHeight()][img.getWidth()];

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                Color color = new Color(img.getRGB(j, i));
                int value = color.getRed();
                if (value >= 48) { // number of tiles in sprite atlas
                    value = 0;
                }
                levelData[i][j] = value;
            }
        }
        return levelData;
    }
}
