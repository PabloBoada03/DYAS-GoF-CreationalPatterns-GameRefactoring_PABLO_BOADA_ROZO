package com.balitechy.spacewar.CSpriteFactory;

import com.balitechy.spacewar.main.Game;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SInputHandler extends KeyAdapter{
	
	private Game game;
	
	public SInputHandler(Game game){
		this.game = game;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
