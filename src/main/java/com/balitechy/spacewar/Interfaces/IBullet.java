package com.balitechy.spacewar.Interfaces;

import java.awt.Graphics;

public interface IBullet {
    void tick();
    void render(Graphics g);
    double getY();
}
