package controller;

public interface GameControllerInterface {
	
	public void startGame();
	public void endGame();
	public void resetGame();
	public void placePipes();
	public void move();
	 public SelectionManager getSelection();
}
