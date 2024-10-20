package controller;

import model.Bird;
import model.Character;
import model.Plane;
import model.SelectionObserver;
import model.Hero;
import model.GameModelInterface;
import model.GameModel;

public class SelectionManager {
	
	private String character;
    private GameModelInterface gameModel;

    public SelectionManager(String character, GameModelInterface gameModel) {
        super();
        this.character = character;
        this.gameModel = gameModel;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
    
    public Character createCharacter(String type) {
        Character character = null;
        if(type.equalsIgnoreCase("bird")) {
            character = new Bird(0, 0, 0, 0, null, null);
        } else if(type.equalsIgnoreCase("plane")) {
            character = new Plane(0, 0, 0, 0, null, null);
        } else if(type.equalsIgnoreCase("hero")) {
            character = new Hero(0, 0, 0, 0, null);
        }
        gameModel.setCharacter(character);  // Update the character in the game model
        return character;
    }

    
}
