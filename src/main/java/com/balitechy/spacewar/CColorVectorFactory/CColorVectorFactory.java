package com.balitechy.spacewar.CColorVectorFactory;

import AGameFactory.AGameFactory;
import com.balitechy.spacewar.Interfaces.IBG;
import com.balitechy.spacewar.Interfaces.IBullet;
import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;

public class CColorVectorFactory extends AGameFactory{
    
    public CColorVectorFactory(Game game) {
        super(game);
    }
    
    @Override
    public IPlayer crearPlayer(double x, double y) {
        return new CVPlayer(x, y, game);
    }

    @Override
    public IBullet crearBullet(double x, double y) {
        return new CVBullet(x, y);
    }

    @Override
    public IBG crearBG() {
        return new CVBackgroundRenderer();
    }
    
}
