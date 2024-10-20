package model;

import java.util.ArrayList;

import controller.GameController;
import view.GameScreen;

public class BirdSkill implements Skill {
    
    private boolean used;
    private GameController gameController;
    
    public BirdSkill(GameController gameController) {
        this.used = false;
        this.gameController = gameController;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public void skill() {
        if (!used) {
            ArrayList<Pipe> pipes = gameController.getPipes();
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                if (!pipe.isPassed() && i % 2 == 0) {
                    pipes.remove(i);
                    used = true;
                    break;
                }
            }
            used = false; //dùng các lần tiếp theo
        }
    }
}
