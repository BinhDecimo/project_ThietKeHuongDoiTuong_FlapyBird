package model;

import java.awt.Image;

public class Pipe extends Character {
    private boolean passed;

    public Pipe(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.passed = false;
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
}
