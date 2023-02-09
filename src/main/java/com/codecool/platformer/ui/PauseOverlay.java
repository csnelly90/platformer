package com.codecool.platformer.ui;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class PauseOverlay {
    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;
    private SoundButton musicButton, sfxButton;

    public PauseOverlay() {
        loadBackground();
        loadSoundButtons();
    }

    private void loadSoundButtons() {
        int soundX = (int) (450 * GameProperties.SCALE);
        int musicY = (int) (145 * GameProperties.SCALE);
        int sfxY = (int) (190 * GameProperties.SCALE);

        musicButton = new SoundButton(soundX, musicY, SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_BUTTON_SIZE, SOUND_BUTTON_SIZE);
    }

    private void loadBackground() {
        background = LoadSave.getSpriteAtlas(LoadSave.PAUSE_BACKGROUND);
        menuWidth = (int) (background.getWidth() * GameProperties.SCALE);
        menuHeight = (int) (background.getHeight() * GameProperties.SCALE);
        menuX = GameProperties.WINDOW_WIDTH / 2 - menuWidth / 2;
        menuY = GameProperties.WINDOW_HEIGHT / 2 - menuHeight / 2;
    }

    public void update() {
        musicButton.update();
        sfxButton.update();
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);

        // sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
    }

    private boolean isInsideButton(MouseEvent e, PauseButton pb) {
        return pb.getHitbox().contains(e.getX(), e.getY());
    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if (isInsideButton(e, musicButton)) {
            musicButton.setMousePressed(true);
        } else if (isInsideButton(e, sfxButton)) {
            sfxButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isInsideButton(e, musicButton)) {
            if (musicButton.isMousePressed()) musicButton.setMuted(!musicButton.isMuted());
        } else if (isInsideButton(e, sfxButton)) {
            if (sfxButton.isMousePressed()) sfxButton.setMuted(!sfxButton.isMuted());
        }
        musicButton.resetMouseState();
        sfxButton.resetMouseState();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);

        if (isInsideButton(e, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isInsideButton(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        }
    }
}
