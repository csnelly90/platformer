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
}
