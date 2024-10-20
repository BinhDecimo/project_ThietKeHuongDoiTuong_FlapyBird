package model;

import java.awt.Image;

public class Bird extends Character {

	public Bird(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
		skill = new BirdSkill();
	}

	@Override
	public void move() {
	    velocityY += gravity;
	    y += velocityY;
	    y = Math.max(y, 0);
	}

	
	public void useSkill() {
		skill.skill();
		
	}
}
