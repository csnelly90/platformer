package com.codecool.platformer.main;

import com.codecool.platformer.inputs.KeyboardInputs;
import com.codecool.platformer.inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img, subImg;

    public GamePanel() {
        this.mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/sprites/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        subImg = img.getSubimage(1*64, 8*40, 64, 40);
        g.drawImage(subImg, (int) xDelta, (int) yDelta, 128, 80, null);
    }

    public void changeXDelta(int value) {
        xDelta += value;
        repaint();
    }

    public void changeYDelta(int value) {
        yDelta += value;
        repaint();
    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }
}
