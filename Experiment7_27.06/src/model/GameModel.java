package model;

import java.util.ArrayList;

public class GameModel implements GameModelInterface {
	
	private boolean gameOver;
	private double score; 
	private Character character;
	private ArrayList<Pipe> pipes;
	private ArrayList<StateObserver> stateOb;
	private ArrayList<SelectionObserver> selectionOb;
	
	public GameModel() {
		super();
		setCharacter(character);		//skill sẽ được character set sau;
		pipes = new ArrayList<Pipe>();
		stateOb = new ArrayList<>();
		selectionOb = new ArrayList<>();
		this.gameOver = false;
		this.score = 0;
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addObserver(StateObserver o) {
		stateOb.add(o);
		
	}

	@Override
	public void removeObserver(StateObserver o) {
		int i = stateOb.indexOf(o);
		if (i >= 0) { stateOb.remove(i); }
		
	}

	@Override
	public void addObserver(SelectionObserver o) {
		selectionOb.add(o);
	}

	@Override
	public void removeObserver(SelectionObserver o) {
		int i = selectionOb.indexOf(o);
		if (i >= 0) { selectionOb.remove(i); }
	}

	@Override
	public void notifyStateObserver() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void notifySelectionObserver() {
		// TODO Auto-generated method stub
		
	}
	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public ArrayList<Pipe> getPipes() {
		return pipes;
	}

	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	

}
