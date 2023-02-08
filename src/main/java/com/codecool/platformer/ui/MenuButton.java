package com.codecool.platformer.ui;

import static com.codecool.platformer.constants.GameProperties.UI.Buttons.*;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPosition, yPosition, rowIndex, imageIndex;
    private int xOffsetCenter = MB_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] images;

    public MenuButton(int xPosition, int yPosition, int rowIndex, Gamestate state) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImages();
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.MENU_BUTTONS);

        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * MB_DEFAULT_WIDTH,
                    rowIndex * MB_DEFAULT_HEIGHT,
                    MB_DEFAULT_WIDTH,
                    MB_DEFAULT_HEIGHT
            );
        }
    }


}
