package com.codecool.platformer.ui;

import java.awt.*;
import java.awt.image.BufferedImage;

import com.codecool.platformer.utils.LoadSave;
import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class VolumeButton extends PauseButton implements Button {
    private BufferedImage[] images;
    private BufferedImage slider;
    private boolean mouseOver, mousePressed;
    private int imageIndex;
    private int sliderButtonX, minSliderX, maxSliderX;

    public VolumeButton(int x, int y, int width, int height) {
        super(x + width / 2, y, VOL_BUTTON_WIDTH, height);
        hitbox.x -= VOL_BUTTON_WIDTH / 2;
        sliderButtonX = x + width / 2;
        this.x = x;
        this.width = width;
        this.minSliderX = x + VOL_BUTTON_WIDTH / 2;
        this.maxSliderX = x + width - VOL_BUTTON_WIDTH / 2;
        loadImages();
    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage temp = LoadSave.getSpriteAtlas(LoadSave.VOLUME_BUTTONS);

        for (int i = 0; i < images.length; i++) {
            images[i] = temp.getSubimage(
                    i * VOL_BUTTON_DEFAULT_WIDTH,
                    0,
                    VOL_BUTTON_DEFAULT_WIDTH,
                    VOL_BUTTON_DEFAULT_HEIGHT
            );
        }

        slider = temp.getSubimage(3 * VOL_BUTTON_DEFAULT_WIDTH, 0, VOL_SLIDER_DEFAULT_WIDTH, VOL_BUTTON_DEFAULT_HEIGHT);
    }

    @Override
    public void resetMouseState() {
        mouseOver = false;
        mousePressed = false;
    }

    public void changeSliderX(int x) {
        if (x < minSliderX) {
            sliderButtonX = minSliderX;
        } else if (x > maxSliderX) {
            sliderButtonX = maxSliderX;
        } else {
            sliderButtonX = x;
        }

        hitbox.x = sliderButtonX - VOL_BUTTON_WIDTH / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(slider, x, y, width, height, null);
        g.drawImage(images[imageIndex], sliderButtonX - VOL_BUTTON_WIDTH / 2, y, VOL_BUTTON_WIDTH, height, null);
    }

    @Override
    public void update() {
        // gets the proper button image from row based on mouse event
        imageIndex = 0;
        if (mouseOver) imageIndex = 1;
        if (mousePressed) imageIndex = 2;
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
}
