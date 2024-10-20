package controller;

import model.Character;

public interface GameControllerInterface {
	
	public void startGame();
	public void endGame();
	public void resetGame();
	public void placePipes();
	public void move();
	public void startSelection();
	public Character createCharacter(String type);
}
