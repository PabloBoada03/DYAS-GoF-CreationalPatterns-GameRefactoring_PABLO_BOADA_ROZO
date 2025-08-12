package com.balitechy.spacewar.Interfaces;

import java.awt.Graphics;

public interface IPlayer {
    double getX();
    void setX(double x);

    double getY();
    void setY(double y);

    void setVelX(double velX);
    void setVelY(double velY);

    void shoot();
    void tick();
    void render(Graphics g);
}
