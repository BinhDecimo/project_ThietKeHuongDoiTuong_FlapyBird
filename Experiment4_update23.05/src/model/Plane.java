package model;

import java.awt.Image;

public class Plane extends Character {
    private int defaultGravity;
    private PlaneSkill skill;

    public Plane(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        this.defaultGravity = gravity; // Initialize default gravity
        skill = new PlaneSkill();
        skill.setPlane(this);
    }

    @Override
    public void move() {
        skill.skill(); // Call skill's effect on every move
        if (!skill.isActive()) {
            velocityY += gravity;
        }
        y += velocityY;
        y = Math.max(y, 0);
    }

    public void useSkill() {
        skill.activate();
    }

    public int getDefaultGravity() {
        return defaultGravity;
    }
}
