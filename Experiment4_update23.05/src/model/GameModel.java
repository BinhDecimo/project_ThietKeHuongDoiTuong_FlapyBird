package model;

import java.util.ArrayList;
import java.util.List;

public class GameModel implements Observable {
    private List<Observer> observers;
    private boolean gameOver;
    private double score;
    private Character character;
    private ArrayList<Pipe> pipes;

    public GameModel(Character character) {
        this.character = character;
        this.pipes = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        notifyObservers();
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        notifyObservers();
    }

    public Character getCharacter() {
        return character;
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
