package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Character {

	protected int x, y, width, height;
	protected int velocityX, velocityY;
	protected int gravity;
	protected Image image;
	protected Skill skill;

	public Character(int x, int y, int width, int height, Image image) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.image = image;
		velocityX = -4;
		velocityY = 0;
		gravity = 1;
	};

	public void display(Graphics g) {
		g.drawImage(image, x, y, width, height, null);
	}

	public Rectangle getBound() { // dùng để kt va chạm đối tượng
		return new Rectangle(x, y, width, height);
	}

	public abstract void move();

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public int getVelocityX() {
		return velocityX;
	}

	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}

	public int getVelocityY() {
		return velocityY;
	}

	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	
	public int getGravity() {
		return gravity;
	}

	public void setGravity(int gravity) {
		this.gravity = gravity;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	
	public void useSkill() {
		skill.skill();
	}

	
	
	
	
	

}
