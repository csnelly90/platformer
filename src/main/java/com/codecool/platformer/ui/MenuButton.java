package com.codecool.platformer.ui;

import static com.codecool.platformer.constants.GameProperties.UI.Buttons.*;
import com.codecool.platformer.constants.Gamestate;
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

    }

}
