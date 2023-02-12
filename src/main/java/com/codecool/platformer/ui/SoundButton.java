package com.codecool.platformer.ui;

import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class SoundButton extends PauseButton implements Button {
    private BufferedImage[][] soundImages;
    private boolean mouseOver, mousePressed;
    private boolean muted;
    private int rowIndex, colIndex;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImages();
    }

    private void loadImages() {
        soundImages = new BufferedImage[2][3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.SOUND_BUTTONS);

        for (int i = 0; i < soundImages.length; i++) {
            for (int j = 0; j < soundImages[i].length; j++) {
                soundImages[i][j] = temp.getSubimage(
                        j * SOUND_BUTTON_DEFAULT_SIZE,
                        i * SOUND_BUTTON_DEFAULT_SIZE,
                        SOUND_BUTTON_DEFAULT_SIZE,
                        SOUND_BUTTON_DEFAULT_SIZE
                );
            }
        }
    }

    @Override
    public void update() {
        // gets the first or second row of button sprites in the image
        if (muted) {
            rowIndex = 1;
        } else {
            rowIndex = 0;
        }

        // gets the proper button image from row based on mouse event
        colIndex = 0;

        if (mouseOver) {
            colIndex = 1;
        }

        if (mousePressed) {
            colIndex = 2;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(
                soundImages[rowIndex][colIndex],
                x,
                y,
                width,
                height,
                null
        );
    }

    @Override
    public void resetMouseState() {
        mouseOver = false;
        mousePressed = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }
}
