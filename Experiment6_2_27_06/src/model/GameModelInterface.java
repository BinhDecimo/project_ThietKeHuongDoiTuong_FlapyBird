package model;

import java.util.ArrayList;

public interface GameModelInterface {
	
	public void initialize();
	public void addObserver(StateObserver o);
	public void removeObserver(StateObserver o);
	public void addObserver(SelectionObserver o);
	public void removeObserver(SelectionObserver o);
	public void notifyStateObserver();
	public void notifySelectionObserver();
	public Character getCharacter();
    public void setCharacter(Character character);
    public ArrayList<Pipe> getPipes();  // Add this method to the interface
    public void setPipes(ArrayList<Pipe> pipes);
    public boolean isGameOver();
    public double getScore();
    public void setGameOver(boolean gameOver);
    public void setScore(double score);

}
