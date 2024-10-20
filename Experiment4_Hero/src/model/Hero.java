package model;

import java.awt.Image;

public class Hero extends Character {
    private HeroSkill skill;

    public Hero(int x, int y, int width, int height, Image image) {
        super(x, y, width, height, image);
        skill = new HeroSkill();
        skill.setHero(this);
    }

    @Override
    public void move() {
        skill.skill(); // Call skill's effect on every move
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
//        velocityX -= 70;
//        x-=velocityX;
//        x = Math.min(x, 0);
    }

    public void useSkill() {
        skill.activate();
    }
    
    public boolean isSkillActive() {
        return skill.isActive();
    }
}
