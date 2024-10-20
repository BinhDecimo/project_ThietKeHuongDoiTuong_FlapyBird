package model;

import java.awt.Image;

import controller.GameController;

public class Bird extends Character {
    private GameController gameController;

    public Bird(int x, int y, int width, int height, Image image, GameController gameController) {
        super(x, y, width, height, image);
        this.gameController = gameController;
        skill = new BirdSkill(gameController);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
        skill = new BirdSkill(gameController);
    }

    @Override
    public void move() {
        velocityY += gravity;
        y += velocityY;
        y = Math.max(y, 0);
    }
}
