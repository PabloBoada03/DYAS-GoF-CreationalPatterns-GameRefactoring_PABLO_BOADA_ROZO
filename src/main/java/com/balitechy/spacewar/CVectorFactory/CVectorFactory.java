package com.balitechy.spacewar.CVectorFactory;

import AGameFactory.AGameFactory;
import com.balitechy.spacewar.Interfaces.IBG;
import com.balitechy.spacewar.Interfaces.IBullet;
import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;

public class CVectorFactory extends AGameFactory{
    
    public CVectorFactory(Game game) {
        super(game);
    }
    
    @Override
    public IPlayer crearPlayer(double x, double y) {
        return new VPlayer(x, y, game);
    }

    @Override
    public IBullet crearBullet(double x, double y) {
        return new VBullet(x, y);
    }

    @Override
    public IBG crearBG() {
        return new VBackgroundRenderer();
    }
    
}
