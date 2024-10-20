package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import model.Bird;
import model.CollisionManager;
import model.Pipe;
import model.Plane;
import model.PlaneSkill;
import model.Skill;
import view.GameScreen;

public class GameController implements ActionListener, KeyListener {
    private GameScreen gameScreen;
    private Plane character;
    private ArrayList<Pipe> pipes;
    private Random random = new Random();
    private Timer gameLoop, placePipeTimer;
    private boolean gameOver = false;
    private double score = 0;
    //private boolean sKeyPressed = false;

    public GameController(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.character = gameScreen.getCharacter();  // Lấy Bird từ GameScreen
        this.pipes = gameScreen.getPipes();  // Lấy pipes từ GameScreen

        gameScreen.addKeyListener(this);
        gameScreen.setFocusable(true);

        placePipeTimer = new Timer(1500, e -> placePipes());
        placePipeTimer.start();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.start();
    }

    public ArrayList<Pipe> getPipes() {
        return pipes;
    }

    private void placePipes() {
        int randomPipeY = (int) (gameScreen.getHeight() / 2 - Math.random() * (gameScreen.getHeight() / 4));
        int openingSpace = gameScreen.getHeight() / 4;

        Pipe topPipe = new Pipe(gameScreen.getWidth(), randomPipeY - gameScreen.getHeight() / 2, 64, 512, gameScreen.getTopPipeImg());
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(gameScreen.getWidth(), topPipe.getY() + topPipe.getHeight() + openingSpace, 64, 512, gameScreen.getBottomPipeImg());
        pipes.add(bottomPipe);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            gameScreen.repaint();
            character.useSkill(); // Gọi phương thức skill() của character trong mỗi vòng lặp game loop
        }
    }

    private void move() {
        character.move();
        for (Pipe pipe : pipes) {
            pipe.move();
            if (!pipe.isPassed() && character.getX() > pipe.getX() + pipe.getWidth()) {
                score += 0.5;
                pipe.setPassed(true);
            }
            if (CollisionManager.checkCollision(character, pipe)) {
                gameOver = true;
                gameScreen.setGameOver(true);
            }
        }
        if (character.getY() > gameScreen.getHeight()) {
            gameOver = true;
            gameScreen.setGameOver(true);
        }
        gameScreen.setScore(score);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (gameOver) {
                resetGame();
            } else {
                character.setVelocityY(-9);
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            //sKeyPressed = true;
            if (character instanceof Plane) {
                Plane plane = (Plane) character;
                plane.useSkill();; // Kích hoạt kỹ năng của máy bay
            }
        }



    }

    private void resetGame() {
        character.reset(gameScreen.getWidth() / 8, gameScreen.getHeight() / 2);
        pipes.clear();
        gameOver = false;
        score = 0;
        gameScreen.setGameOver(false);
        gameLoop.restart();
        placePipeTimer.restart();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
//    	if (e.getKeyCode() == KeyEvent.VK_S) {
//            sKeyPressed = false;
//            if (character instanceof Plane) {
//                Plane plane = (Plane) character;
//                plane.move(); // Hủy kích hoạt kỹ năng của máy bay
//            }
//        }
    }
}
