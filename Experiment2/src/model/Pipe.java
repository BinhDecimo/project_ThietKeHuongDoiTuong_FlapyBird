package model;

import java.awt.Image;
import java.util.ArrayList;

public class Pipe extends Character {
	
	public boolean passed;

	public Pipe(int x, int y, int width, int height, Image image) {
		super(x, y, width, height, image);
		passed = false;
	}

	@Override
	public void move() {
		x += velocityX;
	}
	
	public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }
    
    public boolean isTopPipe() {
		return y < 0;
	}
}
