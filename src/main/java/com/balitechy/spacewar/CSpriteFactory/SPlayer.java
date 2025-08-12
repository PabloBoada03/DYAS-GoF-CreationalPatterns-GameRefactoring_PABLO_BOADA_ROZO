package com.balitechy.spacewar.CSpriteFactory;

import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SPlayer implements IPlayer {

    private double x;
    private double y;
    private double velX;
    private double velY;

    public static final int WIDTH = 56;
    public static final int HEIGHT = 28;

    private BufferedImage image;
    private Game game;

    public SPlayer(double x, double y, BufferedImage image, Game game) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.game = game;
    }

    @Override
    public double getX() { return x; }

    @Override
    public void setX(double x) { this.x = x; }

    @Override
    public double getY() { return y; }

    @Override
    public void setY(double y) { this.y = y; }

    @Override
    public void setVelX(double velX) { this.velX = velX; }

    @Override
    public void setVelY(double velY) { this.velY = velY; }

    @Override
    public void shoot() {
        game.getBullets().addBullet(
            game.getFactory().crearBullet(x + (WIDTH / 2) - 5, y - 18)
        );
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if (x <= 0) x = 0;
        if (x >= (Game.WIDTH * Game.SCALE) - WIDTH) x = (Game.WIDTH * Game.SCALE) - WIDTH;
        if (y <= 0) y = 0;
        if (y >= (Game.HEIGHT * Game.SCALE) - HEIGHT) y = (Game.HEIGHT * Game.SCALE) - HEIGHT;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
}
