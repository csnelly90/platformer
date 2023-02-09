package com.codecool.platformer.ui;

import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class UrmButton extends PauseButton implements Button {
    private BufferedImage[] images;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, imageIndex;

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.URM_BUTTONS);

        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * URM_BUTTON_DEFAULT_SIZE,
                    rowIndex * URM_BUTTON_DEFAULT_SIZE,
                    URM_BUTTON_DEFAULT_SIZE,
                    URM_BUTTON_DEFAULT_SIZE
            );
        }
    }

    @Override
    public void resetMouseState() {
        mouseOver = false;
        mousePressed = false;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                images[imageIndex],
                x,
                y,
                URM_BUTTON_SIZE,
                URM_BUTTON_SIZE,
                null
        );
    }

    @Override
    public void update() {
        // gets the proper button image from row based on mouse event
        imageIndex = 0;
        if (mouseOver) imageIndex = 1;
        if (mousePressed) imageIndex = 2;
    }
}
