package com.balitechy.spacewar.CSpriteFactory;

import com.balitechy.spacewar.main.Game;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.balitechy.spacewar.Interfaces.IBullet;

public class SBullet implements IBullet{
	
	private double x;
	private double y;
	public static final int WIDTH = 11;
	public static final int HEIGHT = 21;
	private BufferedImage image;
	
	public SBullet(double x, double y, Game game){
		this.x = x;
		this.y = y;
		image = game.getSprites().getImage(35, 52, WIDTH, HEIGHT);
	}
	@Override
	public void tick(){
		y -= 5;
	}
	@Override
	public void render(Graphics g){
		g.drawImage(image, (int) x, (int) y, null);
	}
	@Override
	public double getY(){
		return y;
	}
}
