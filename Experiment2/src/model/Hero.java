package model;

import java.awt.Image;

public class Hero extends Character {

	public Hero(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
		skill = new HeroSkill();
	}

	@Override
	public void move() {
		velocityY += gravity;
		y += velocityY;
		y = Math.max(y, 0);
		
	}
	
	
	
	

}
