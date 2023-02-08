package com.codecool.platformer.gamestates;

import com.codecool.platformer.main.Game;
import com.codecool.platformer.ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public boolean isInsideButton(MouseEvent e, MenuButton mb) {
        return mb.getHitbox().contains(e.getX(), e.getY());
    }

    public Game getGame() {
        return game;
    }
}
