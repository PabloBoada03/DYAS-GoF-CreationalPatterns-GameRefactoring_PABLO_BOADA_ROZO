package com.balitechy.spacewar.CSpriteFactory;

import com.balitechy.spacewar.Interfaces.IBullet;
import java.awt.Graphics;
import java.util.LinkedList;

public class SBulletController {

    private LinkedList<IBullet> bullets = new LinkedList<>();

    public void tick() {
        for (int i = 0; i < bullets.size(); i++) {
            IBullet b = bullets.get(i);
            if (b.getY() < 0) {
                removeBullet(b);
                i--; // evitar saltarse elementos
            } else {
                b.tick();
            }
        }
    }

    public void render(Graphics g) {
        for (IBullet b : bullets) {
            b.render(g);
        }
    }

    public void addBullet(IBullet bullet) {
        bullets.add(bullet);
    }

    public void removeBullet(IBullet bullet) {
        bullets.remove(bullet);
    }
}
