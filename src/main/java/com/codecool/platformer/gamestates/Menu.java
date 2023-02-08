package com.codecool.platformer.gamestates;

import com.codecool.platformer.constants.GameProperties;
import com.codecool.platformer.constants.Gamestate;
import com.codecool.platformer.main.Game;
import com.codecool.platformer.ui.MenuButton;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Menu extends State implements StateMethods {
    private MenuButton[] buttons = new MenuButton[3];

    public Menu(Game game) {
        super(game);
        loadButtons();
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(GameProperties.WINDOW_WIDTH / 2, (int) (150 * GameProperties.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(GameProperties.WINDOW_WIDTH / 2, (int) (220 * GameProperties.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(GameProperties.WINDOW_WIDTH / 2, (int) (290 * GameProperties.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb: buttons) {
            mb.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        for (MenuButton mb: buttons) {
            mb.draw(g);
        }
    }

    private void resetButtons() {
        for (MenuButton mb: buttons) {
            mb.resetMouseState();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb: buttons) {
            if (isInsideButton(e, mb)) {
                mb.setMousePressed(true);
                break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb: buttons) {
            if (isInsideButton(e, mb)) {
                if (mb.isMousePressed()) mb.applyGamestate();
                break;
            }
        }
        resetButtons();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb: buttons) {
            mb.setMouseOver(false);
        }

        for (MenuButton mb: buttons) {
            if (isInsideButton(e, mb)) {
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            Gamestate.state = Gamestate.PLAYING;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
