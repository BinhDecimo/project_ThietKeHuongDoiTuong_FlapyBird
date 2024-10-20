package model;

import java.awt.Image;

public class Plane extends Character {

	public Plane(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
		skill = new PlaneSkill();
	}

	@Override
	public void move() {
		velocityY += gravity;
		y += velocityY;
		y = Math.max(y, 0);
		
	}

}
