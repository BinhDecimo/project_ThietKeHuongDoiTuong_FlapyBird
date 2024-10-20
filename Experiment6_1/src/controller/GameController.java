package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import model.Character;
import controller.CollisionManager;
import model.Pipe;
import view.GameScreen;
import view.RankingScreen;
import model.GameModelInterface;

public class GameController implements GameControllerInterface, ActionListener, KeyListener {
    private GameScreen gameScreen;
    private GameModelInterface gameModel;
    private Character character;
    private ArrayList<Pipe> pipes;
    private Random random = new Random();
    private Timer gameLoop, placePipeTimer;
    private JFrame mainFrame;
    
    private SelectionManager selection;

    public GameController(GameModelInterface gameModel, JFrame mainFrame) {
        this.gameModel = gameModel;
        this.character = gameModel.getCharacter();
        this.pipes = gameModel.getPipes();
        this.mainFrame = mainFrame;
        this.selection = new SelectionManager("", gameModel);  // Initialize selection here
    }


    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    @Override
    public void placePipes() {
        int randomPipeY = (int) (gameScreen.getHeight() / 2 - Math.random() * (gameScreen.getHeight() / 4));
        int openingSpace = gameScreen.getHeight() / 4;

        Pipe topPipe = new Pipe(gameScreen.getWidth(), randomPipeY - gameScreen.getHeight() / 2, 64, 512, gameScreen.getTopPipeImg());
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(gameScreen.getWidth(), topPipe.getY() + topPipe.getHeight() + openingSpace, 64, 512, gameScreen.getBottomPipeImg());
        pipes.add(bottomPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameModel.isGameOver()) {
            move();
            gameScreen.repaint();
        }
    }

    @Override
    public void move() {
        character.move();
        for (Pipe pipe : pipes) {
            pipe.move();
            if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
                gameModel.setScore(gameModel.getScore() + 0.5);
                pipe.setPassed(true);
            }
            if (CollisionManager.checkCollision(character, pipe)) {
                endGame();
            }
        }
        if (character.getY() > gameScreen.getHeight()) {
            endGame();
        }
    }

    @Override
    public void startGame() {
        gameScreen = new GameScreen(mainFrame, gameModel, this);
        mainFrame.setContentPane(gameScreen);
        mainFrame.revalidate();
        gameScreen.requestFocusInWindow();

        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    @Override
    public void endGame() {
        gameModel.setGameOver(true);
        placePipeTimer.stop();
        gameLoop.stop();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameModel.isGameOver()) {
                resetGame();
            } else {
                character.setVelocityY(-9);
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_S) { // Key 'S' to activate skill
            if (!character.isSkillOnCooldown()) {
                character.useSkill(gameModel);
            }
        }
    }


    @Override
    public void resetGame() {
        character.reset(gameScreen.getWidth() / 8, gameScreen.getHeight() / 2);
        character.resetSkill(); // Reset skill khi trò chơi được reset
        pipes.clear();
        gameModel.setGameOver(false);
        gameModel.setScore(0);
        gameLoop.restart();
        placePipeTimer.restart();
    }
    

    public SelectionManager getSelection() {
		return selection;
	}

	public void setSelection(SelectionManager selection) {
		this.selection = selection;
	}

	@Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
