package com.codecool.platformer.ui;

import com.codecool.platformer.utils.LoadSave;

import java.awt.image.BufferedImage;

import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class SoundButton extends PauseButton {
    private BufferedImage[][] soundImages;

    public SoundButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImages();
    }


}
