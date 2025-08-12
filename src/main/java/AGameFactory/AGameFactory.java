package AGameFactory;

import com.balitechy.spacewar.Interfaces.IBG;
import com.balitechy.spacewar.Interfaces.IBullet;
import com.balitechy.spacewar.Interfaces.IPlayer;
import com.balitechy.spacewar.main.Game;

public abstract class AGameFactory {

    protected Game game;

    public AGameFactory(Game game) {
        this.game = game;
    }

    public abstract IPlayer crearPlayer(double x, double y);
    public abstract IBullet crearBullet(double x, double y);
    public abstract IBG crearBG();
}
 