package model;

import java.util.ArrayList;
import view.GameScreen;

public class BirdSkill implements Skill {
	
	private boolean used;

	public BirdSkill() {
		this.used = false;
	}

	public boolean isUsed() {
		return used;
	}

	@Override
	public void skill() {
	    if (!used) {
	        GameScreen gameScreen = GameScreen.getInstance();
	        if (gameScreen != null) {
	            ArrayList<Pipe> pipes = gameScreen.getPipes();
	            for (int i = 0; i < pipes.size(); i++) {
	                Pipe pipe = pipes.get(i);
	                if (!pipe.isPassed() && i % 2 == 0) {
	                    pipes.remove(i);
	                    used = true;
	                    //gameScreen.setPipes(pipes); // Cập nhật lại danh sách ống trong GameScreen
	                    break;
	                }
	            }
	            used = false; //dùng các lần tiếp theo
	        }
	    }
	}

}
