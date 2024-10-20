package controller;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import model.Bird;
import model.Character;
import controller.CollisionManager;
import model.Pipe;
import model.Plane;
import view.GameScreen;
import view.RankingScreen;
import view.SelectionScreen;
import model.GameModelInterface;
import model.Hero;

public class GameController implements GameControllerInterface, ActionListener, KeyListener {
    
    private GameScreen gameScreen;
    private GameModelInterface gameModel;
    private Character character;
    private ArrayList<Pipe> pipes;
    private Random random = new Random();
    private Timer gameLoop, placePipeTimer;
    private JFrame mainFrame;

    public GameController(GameModelInterface gameModel, JFrame mainFrame) {
        this.gameModel = gameModel;
        this.character = gameModel.getCharacter();
        this.pipes = gameModel.getPipes();
        this.mainFrame = mainFrame;
    }

    public void startSelection() {
        SelectionScreen selectionScreen = new SelectionScreen(gameModel, this);
        mainFrame.setContentPane(selectionScreen);
        mainFrame.revalidate();
        selectionScreen.requestFocusInWindow();
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
        this.character = gameModel.getCharacter();
        if (character == null) {
            System.out.println("Character is null in startGame");
            return;
        }

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

        // Create and display the RankingScreen
        RankingScreen rankingScreen = new RankingScreen(mainFrame, gameModel, this);
        mainFrame.setContentPane(rankingScreen);
        mainFrame.revalidate();
        rankingScreen.requestFocusInWindow();
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
                character.useSkill(gameModel); // Use the correct skill method
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

    //khởi tạo nhân vật để chơi game
    public Character createCharacter(String type) {
        Character character = null;
        if(type.equalsIgnoreCase("bird")) {
            character = new Bird(45, 320, 34, 24, null, gameModel);
        } else if(type.equalsIgnoreCase("plane")) {
            character = new Plane(45, 320, 34, 24, null, gameModel);
        } else if(type.equalsIgnoreCase("hero")) {
            character = new Hero(45, 320, 34, 24, null);
        }
        return character;
    }
    
    

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
