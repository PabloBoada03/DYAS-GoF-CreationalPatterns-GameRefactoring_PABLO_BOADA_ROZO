package com.balitechy.spacewar.CSpriteFactory;

import AGameFactory.AGameFactory;
import com.balitechy.spacewar.Interfaces.IBG;
import com.balitechy.spacewar.Interfaces.IBullet;
import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;

import java.awt.image.BufferedImage;

public class CSpriteFactory extends AGameFactory {

    private Game game;

    public CSpriteFactory(Game game) {
        super(game);
        this.game = game;
    }

    @Override
    public IPlayer crearPlayer(double x, double y) {
        BufferedImage img = game.getSprites().getImage(
                219, 304,
                SPlayer.WIDTH, SPlayer.HEIGHT
        );
        return new SPlayer(x, y, img, game);
    }

    @Override
    public IBullet crearBullet(double x, double y) {
        return new SBullet(x, y, game);
    }

    @Override
    public IBG crearBG() {
        return new SBackgroundRenderer();
    }
}
