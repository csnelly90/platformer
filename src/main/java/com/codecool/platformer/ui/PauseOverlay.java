package com.codecool.platformer.ui;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.gamestates.Playing;
import com.codecool.platformer.utils.LoadSave;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static com.codecool.platformer.constants.GameProperties.UI.PauseButtons.*;

public class PauseOverlay {
    private Playing playing;
    private BufferedImage background;
    private int menuX, menuY, menuWidth, menuHeight;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuButton, replayButton, unpauseButton;
    private VolumeButton volumeButton;

    public PauseOverlay(Playing playing) {
        this.playing = playing;
        loadBackground();
        loadSoundButtons();
        loadUrmButtons();
        loadVolumeButton();
    }

    private void loadVolumeButton() {
        int volX = (int) (309 * GameProperties.SCALE);
        int volY = (int) (278 * GameProperties.SCALE);
        volumeButton = new VolumeButton(volX, volY, VOL_SLIDER_WIDTH, VOL_BUTTON_HEIGHT);
    }

    private void loadUrmButtons() {
        int menuX = (int) (316 * GameProperties.SCALE);
        int replayX = (int) (388 * GameProperties.SCALE);
        int unpauseX = (int) (460 * GameProperties.SCALE);
        int urmButtonY = (int) (330 * GameProperties.SCALE);

        menuButton = new UrmButton(menuX, urmButtonY, URM_BUTTON_SIZE, URM_BUTTON_SIZE, 2);
        replayButton = new UrmButton(replayX, urmButtonY, URM_BUTTON_SIZE, URM_BUTTON_SIZE, 1);
        unpauseButton = new UrmButton(unpauseX, urmButtonY, URM_BUTTON_SIZE, URM_BUTTON_SIZE, 0);

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
        // sound buttons
        musicButton.update();
        sfxButton.update();
        volumeButton.update();

        // urm buttons
        menuButton.update();
        replayButton.update();
        unpauseButton.update();
    }

    public void draw(Graphics g) {
        // background
        g.drawImage(background, menuX, menuY, menuWidth, menuHeight, null);

        // sound buttons
        musicButton.draw(g);
        sfxButton.draw(g);
        volumeButton.draw(g);

        // urm buttons
        menuButton.draw(g);
        replayButton.draw(g);
        unpauseButton.draw(g);
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
        } else if (isInsideButton(e, menuButton)) {
            menuButton.setMousePressed(true);
        } else if (isInsideButton(e, replayButton)) {
            replayButton.setMousePressed(true);
        } else if (isInsideButton(e, unpauseButton)) {
            unpauseButton.setMousePressed(true);
        } else if (isInsideButton(e, volumeButton)) {
            volumeButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isInsideButton(e, musicButton)) {
            if (musicButton.isMousePressed()) musicButton.setMuted(!musicButton.isMuted());
        } else if (isInsideButton(e, sfxButton)) {
            if (sfxButton.isMousePressed()) sfxButton.setMuted(!sfxButton.isMuted());
        } else if (isInsideButton(e, menuButton)) {
            if (menuButton.isMousePressed()) {
                Gamestate.state = Gamestate.MENU;
                playing.unpauseGame();
            }
        } else if (isInsideButton(e, replayButton)) {
            if (replayButton.isMousePressed()) System.out.println("Replay level to be implemented...");
        } else if (isInsideButton(e, unpauseButton)) {
            if (unpauseButton.isMousePressed()) playing.unpauseGame();
        }

        // reset mouse states for all buttons
        musicButton.resetMouseState();
        sfxButton.resetMouseState();
        menuButton.resetMouseState();
        replayButton.resetMouseState();
        unpauseButton.resetMouseState();
        volumeButton.resetMouseState();
    }

    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuButton.setMouseOver(false);
        replayButton.setMouseOver(false);
        unpauseButton.setMouseOver(false);
        volumeButton.setMouseOver(false);

        if (isInsideButton(e, musicButton)) {
            musicButton.setMouseOver(true);
        } else if (isInsideButton(e, sfxButton)) {
            sfxButton.setMouseOver(true);
        } else if (isInsideButton(e, menuButton)) {
            menuButton.setMouseOver(true);
        } else if (isInsideButton(e, replayButton)) {
            replayButton.setMouseOver(true);
        } else if (isInsideButton(e, unpauseButton)) {
            unpauseButton.setMouseOver(true);
        } else if (isInsideButton(e, volumeButton)) {
            volumeButton.setMouseOver(true);
    }
}
