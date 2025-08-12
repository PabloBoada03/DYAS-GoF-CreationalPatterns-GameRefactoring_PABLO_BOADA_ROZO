package com.balitechy.spacewar.CColorVectorFactory;

import com.balitechy.spacewar.Interfaces.IBullet;
import java.awt.Color;
import java.awt.Graphics;

public class CVBullet implements IBullet {
    private double x, y;

    public CVBullet(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override public void tick() { y -= 5; }
    @Override public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine((int)x, (int)y, (int)x, (int)y - 5);
    }
    @Override public double getY() { return y; }
}
