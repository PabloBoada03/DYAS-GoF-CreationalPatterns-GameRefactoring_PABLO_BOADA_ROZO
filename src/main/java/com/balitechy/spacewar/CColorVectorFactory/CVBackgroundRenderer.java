package com.balitechy.spacewar.CColorVectorFactory;

import com.balitechy.spacewar.Interfaces.IBG;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

public class CVBackgroundRenderer implements IBG {
    @Override
    public void render(Graphics g, Canvas c) throws IOException {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
}
