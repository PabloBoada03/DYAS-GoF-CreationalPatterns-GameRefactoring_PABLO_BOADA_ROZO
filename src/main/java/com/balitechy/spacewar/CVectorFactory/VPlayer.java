package com.balitechy.spacewar.CVectorFactory;

import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;
import java.awt.Color;
import java.awt.Graphics;

public class VPlayer implements IPlayer {
    private double x, y, velX, velY;
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;
    private Game game;

    public VPlayer(double x, double y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    @Override public double getX() { return x; }
    @Override public void setX(double x) { this.x = x; }
    @Override public double getY() { return y; }
    @Override public void setY(double y) { this.y = y; }
    @Override public void setVelX(double velX) { this.velX = velX; }
    @Override public void setVelY(double velY) { this.velY = velY; }

    @Override
    public void shoot() {
        game.getBullets().addBullet(
            game.getFactory().crearBullet(x + WIDTH / 2, y - 10)
        );
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
        if (x < 0) x = 0;
        if (x > Game.WIDTH * Game.SCALE - WIDTH) x = Game.WIDTH * Game.SCALE - WIDTH;
        if (y < 0) y = 0;
        if (y > Game.HEIGHT * Game.SCALE - HEIGHT) y = Game.HEIGHT * Game.SCALE - HEIGHT;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        int[] px = {(int)x, (int)x + WIDTH/2, (int)x + WIDTH};
        int[] py = {(int)y + HEIGHT, (int)y, (int)y + HEIGHT};
        g.drawPolygon(px, py, 3);
    }
}
